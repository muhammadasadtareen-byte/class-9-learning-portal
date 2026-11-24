package com.example.data.document

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.provider.OpenableColumns
import android.util.Log
import com.example.data.model.MaterialCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

data class IngestedDocument(
    val fileName: String,
    val mimeType: String,
    val fileSizeBytes: Long,
    val savedLocalPath: String,
    val inferredCategory: MaterialCategory,
    val pageCount: Int,
    val extractedPlainText: String,
    val renderedBitmaps: List<Bitmap>,
    val unreadablePagesNote: String
)

object DocumentIngestionEngine {
    private const val TAG = "DocIngestion"
    private const val MAX_PDF_RENDER_PAGES = 6
    private const val MAX_BITMAP_EDGE = 1280

    suspend fun ingestFromUri(
        context: Context,
        uri: Uri,
        selectedCategory: MaterialCategory? = null
    ): IngestedDocument = withContext(Dispatchers.IO) {
        val resolver = context.contentResolver
        var fileName = "document_${System.currentTimeMillis()}"
        var fileSize = 0L

        resolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                if (nameIndex >= 0) fileName = cursor.getString(nameIndex) ?: fileName
                if (sizeIndex >= 0) fileSize = cursor.getLong(sizeIndex)
            }
        }

        val mimeType = resolver.getType(uri) ?: inferMimeType(fileName)
        val materialsDir = File(context.filesDir, "materials").apply { mkdirs() }
        val safeName = fileName.replace(Regex("[^a-zA-Z0-9._-]"), "_").take(120)
        val localFile = File(materialsDir, "${System.currentTimeMillis()}_$safeName")

        resolver.openInputStream(uri)?.use { input ->
            FileOutputStream(localFile).use { output ->
                input.copyTo(output)
            }
        } ?: throw IllegalStateException("Unable to read the selected file.")

        if (fileSize <= 0L) fileSize = localFile.length()

        val category = selectedCategory ?: inferCategory(fileName, mimeType)

        return@withContext when {
            mimeType.contains("pdf", ignoreCase = true) || fileName.endsWith(".pdf", ignoreCase = true) -> {
                processPdf(localFile, fileName, mimeType, fileSize, category)
            }
            mimeType.contains("wordprocessingml", ignoreCase = true) || fileName.endsWith(".docx", ignoreCase = true) -> {
                processDocx(localFile, fileName, mimeType, fileSize, category)
            }
            mimeType.startsWith("image/", ignoreCase = true) ||
                fileName.endsWith(".png", true) ||
                fileName.endsWith(".jpg", true) ||
                fileName.endsWith(".jpeg", true) ||
                fileName.endsWith(".webp", true) -> {
                processImage(localFile, fileName, mimeType, fileSize, category)
            }
            else -> {
                processPlainText(localFile, fileName, mimeType, fileSize, category)
            }
        }
    }

    private fun processPdf(
        file: File,
        fileName: String,
        mimeType: String,
        fileSize: Long,
        category: MaterialCategory
    ): IngestedDocument {
        val bitmaps = mutableListOf<Bitmap>()
        var pageCount = 0
        val warnings = mutableListOf<String>()

        try {
            ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY).use { pfd ->
                PdfRenderer(pfd).use { renderer ->
                    pageCount = renderer.pageCount
                    if (pageCount == 0) {
                        warnings.add("PDF contains 0 readable pages.")
                    } else {
                        // Render key pages (cover, TOC, early chapter pages) for Gemini Vision OCR
                        val pagesToRender = minOf(pageCount, MAX_PDF_RENDER_PAGES)
                        for (index in 0 until pagesToRender) {
                            try {
                                renderer.openPage(index).use { page ->
                                    val scale = minOf(
                                        MAX_BITMAP_EDGE.toFloat() / maxOf(page.width, 1),
                                        MAX_BITMAP_EDGE.toFloat() / maxOf(page.height, 1),
                                        2.0f
                                    )
                                    val w = (page.width * scale).toInt().coerceAtLeast(300)
                                    val h = (page.height * scale).toInt().coerceAtLeast(300)
                                    val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
                                    Canvas(bitmap).drawColor(Color.WHITE)
                                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                                    bitmaps.add(bitmap)
                                }
                            } catch (e: Exception) {
                                Log.w(TAG, "Failed rendering PDF page ${index + 1}", e)
                                warnings.add("Page ${index + 1} could not be rendered (${e.localizedMessage ?: "error"}). Never silently ignored.")
                            }
                        }
                        if (pageCount > MAX_PDF_RENDER_PAGES) {
                            warnings.add("Sampled first $MAX_PDF_RENDER_PAGES of $pageCount PDF pages for quick vision indexing. Upload chapter-specific excerpts or images for deep page-by-page OCR of later pages.")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "PdfRenderer failed on $fileName", e)
            warnings.add("PDF could not be rendered locally (${e.localizedMessage ?: "encrypted or malformed PDF"}). Re-upload an unlocked PDF or page images.")
        }

        return IngestedDocument(
            fileName = fileName,
            mimeType = mimeType,
            fileSizeBytes = fileSize,
            savedLocalPath = file.absolutePath,
            inferredCategory = category,
            pageCount = pageCount,
            extractedPlainText = "",
            renderedBitmaps = bitmaps,
            unreadablePagesNote = warnings.joinToString(" ")
        )
    }

    private fun processDocx(
        file: File,
        fileName: String,
        mimeType: String,
        fileSize: Long,
        category: MaterialCategory
    ): IngestedDocument {
        val textBuilder = StringBuilder()
        var warning = ""
        try {
            ZipInputStream(file.inputStream()).use { zis ->
                var entry = zis.nextEntry
                while (entry != null) {
                    if (entry.name.equals("word/document.xml", ignoreCase = true)) {
                        val xml = zis.bufferedReader(Charsets.UTF_8).readText()
                        val cleaned = xml
                            .replace(Regex("</w:p>"), "\n")
                            .replace(Regex("<w:tab/>"), "\t")
                            .replace(Regex("<[^>]+>"), " ")
                            .replace("&amp;", "&")
                            .replace("&lt;", "<")
                            .replace("&gt;", ">")
                            .replace("&quot;", "\"")
                            .replace("&apos;", "'")
                            .replace(Regex("[ ]{2,}"), " ")
                            .replace(Regex("\n{3,}"), "\n\n")
                            .trim()
                        textBuilder.append(cleaned)
                        break
                    }
                    entry = zis.nextEntry
                }
            }
            if (textBuilder.isBlank()) {
                warning = "No readable text found inside word/document.xml. If this DOCX contains embedded scanned images, export it as PDF or images for OCR."
            }
        } catch (e: Exception) {
            Log.e(TAG, "DOCX extraction failed", e)
            warning = "Could not extract DOCX XML (${e.localizedMessage ?: "invalid archive"})."
        }

        val text = textBuilder.toString().take(80000)
        val approxPages = (text.length / 2200).coerceAtLeast(if (text.isNotEmpty()) 1 else 0)
        return IngestedDocument(
            fileName = fileName,
            mimeType = mimeType,
            fileSizeBytes = fileSize,
            savedLocalPath = file.absolutePath,
            inferredCategory = category,
            pageCount = approxPages,
            extractedPlainText = text,
            renderedBitmaps = emptyList(),
            unreadablePagesNote = warning
        )
    }

    private fun processImage(
        file: File,
        fileName: String,
        mimeType: String,
        fileSize: Long,
        category: MaterialCategory
    ): IngestedDocument {
        var warning = ""
        val bitmaps = mutableListOf<Bitmap>()
        try {
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeFile(file.absolutePath, bounds)
            var sampleSize = 1
            while (bounds.outWidth / sampleSize > MAX_BITMAP_EDGE || bounds.outHeight / sampleSize > MAX_BITMAP_EDGE) {
                sampleSize *= 2
            }
            val decodeOpts = BitmapFactory.Options().apply { inSampleSize = sampleSize }
            val bmp = BitmapFactory.decodeFile(file.absolutePath, decodeOpts)
            if (bmp != null) {
                bitmaps.add(bmp)
            } else {
                warning = "Image could not be decoded for OCR. Please upload a clear PNG or JPEG."
            }
        } catch (e: Exception) {
            warning = "Image decode failed: ${e.localizedMessage ?: "unsupported image"}."
        }

        return IngestedDocument(
            fileName = fileName,
            mimeType = mimeType,
            fileSizeBytes = fileSize,
            savedLocalPath = file.absolutePath,
            inferredCategory = category,
            pageCount = if (bitmaps.isNotEmpty()) 1 else 0,
            extractedPlainText = "",
            renderedBitmaps = bitmaps,
            unreadablePagesNote = warning
        )
    }

    private fun processPlainText(
        file: File,
        fileName: String,
        mimeType: String,
        fileSize: Long,
        category: MaterialCategory
    ): IngestedDocument {
        return try {
            val text = file.readText(Charsets.UTF_8).take(80000)
            val warning = if (text.isBlank()) "Uploaded text file is empty." else ""
            IngestedDocument(
                fileName = fileName,
                mimeType = mimeType,
                fileSizeBytes = fileSize,
                savedLocalPath = file.absolutePath,
                inferredCategory = category,
                pageCount = (text.length / 2200).coerceAtLeast(1),
                extractedPlainText = text,
                renderedBitmaps = emptyList(),
                unreadablePagesNote = warning
            )
        } catch (e: Exception) {
            IngestedDocument(
                fileName = fileName,
                mimeType = mimeType,
                fileSizeBytes = fileSize,
                savedLocalPath = file.absolutePath,
                inferredCategory = category,
                pageCount = 0,
                extractedPlainText = "",
                renderedBitmaps = emptyList(),
                unreadablePagesNote = "Unsupported binary format (${e.localizedMessage ?: "cannot read as UTF-8"}). Convert to PDF, DOCX, or image."
            )
        }
    }

    private fun inferMimeType(fileName: String): String {
        val lower = fileName.lowercase()
        return when {
            lower.endsWith(".pdf") -> "application/pdf"
            lower.endsWith(".docx") -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            lower.endsWith(".doc") -> "application/msword"
            lower.endsWith(".png") -> "image/png"
            lower.endsWith(".jpg") || lower.endsWith(".jpeg") -> "image/jpeg"
            lower.endsWith(".txt") -> "text/plain"
            else -> "application/octet-stream"
        }
    }

    private fun inferCategory(fileName: String, mimeType: String): MaterialCategory {
        val lower = fileName.lowercase()
        return when {
            lower.contains("slo") || lower.contains("curriculum") || lower.contains("syllabus") -> MaterialCategory.CURRICULUM_SLO
            lower.contains("model") -> MaterialCategory.MODEL_PAPER
            lower.contains("past") -> MaterialCategory.PAST_PAPER
            lower.contains("marking") || lower.contains("rubric") -> MaterialCategory.MARKING_SCHEME
            lower.contains("note") -> MaterialCategory.STUDY_NOTES
            lower.contains("scan") -> MaterialCategory.SCANNED_BOOK
            mimeType.startsWith("image/") -> MaterialCategory.PAGE_IMAGE
            lower.endsWith(".docx") || lower.endsWith(".doc") -> MaterialCategory.WORD_DOC
            else -> MaterialCategory.TEXTBOOK
        }
    }
}
