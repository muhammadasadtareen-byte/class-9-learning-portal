package com.example.data.pdf

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.text.Layout
import android.text.StaticLayout
import android.text.TextDirectionHeuristics
import android.text.TextPaint
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import com.example.R
import com.example.data.model.Chapter
import com.example.data.model.McqBank
import com.example.data.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class PdfExportType(val title: String, val fileSlug: String) {
    COMPLETE_NOTES("Complete Chapter Notes", "complete_notes"),
    SHORT_NOTES("Short Revision Notes", "short_notes"),
    SOLVED_EXERCISES("Solved Textbook Exercises", "solved_exercises"),
    DEFINITIONS("Definitions & Terminology", "definitions"),
    IMPORTANT_QUESTIONS("Important Examination Questions", "important_questions"),
    MCQS_WITH_ANSWERS("MCQs with Answers & Explanations", "mcqs_with_answers"),
    CHAPTER_TEST("Chapter Examination Test Paper", "chapter_test"),
    ANSWER_KEY("Separate Answer Key", "answer_key"),
    FULL_CHAPTER_PACKAGE("Complete Chapter Study Package", "complete_package")
}

data class ExportedPdfResult(
    val file: File,
    val contentUri: Uri,
    val pageCount: Int,
    val exportType: PdfExportType
)

object AcademicPdfExporter {
    // Standard A4 dimensions in PostScript points (72 dpi): 595 x 842
    private const val PAGE_WIDTH = 595
    private const val PAGE_HEIGHT = 842
    private const val MARGIN_H = 44f
    private const val MARGIN_TOP = 56f
    private const val MARGIN_BOTTOM = 54f
    private const val CONTENT_WIDTH = (PAGE_WIDTH - MARGIN_H * 2).toInt()

    private val emeraldColor = Color.rgb(10, 66, 63)
    private val amberColor = Color.rgb(154, 101, 16)
    private val inkColor = Color.rgb(23, 33, 32)
    private val mutedInk = Color.rgb(78, 92, 90)
    private val dividerColor = Color.rgb(214, 206, 192)
    private val tintBg = Color.rgb(245, 241, 234)

    suspend fun exportChapterPdf(
        context: Context,
        subject: Subject,
        chapter: Chapter,
        mcqBank: McqBank?,
        exportType: PdfExportType
    ): Result<ExportedPdfResult> = withContext(Dispatchers.IO) {
        try {
            val sections = buildSections(subject, chapter, mcqBank, exportType)
            val bodyTypeface = ResourcesCompat.getFont(context, R.font.plus_jakarta_sans) ?: Typeface.SANS_SERIF
            val headingTypeface = ResourcesCompat.getFont(context, R.font.fraunces) ?: Typeface.SERIF
            val urduTypeface = ResourcesCompat.getFont(context, R.font.noto_nastaliq_urdu) ?: Typeface.SANS_SERIF

            val pdf = PdfDocument()
            val pagesMetadata = mutableListOf<String>()

            val titlePaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE
                textSize = 16.5f
                typeface = Typeface.create(headingTypeface, Typeface.BOLD)
            }
            val subHeaderPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.rgb(224, 240, 236)
                textSize = 9.5f
                typeface = bodyTypeface
            }
            val sectionPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
                color = emeraldColor
                textSize = 13f
                typeface = Typeface.create(headingTypeface, Typeface.BOLD)
            }
            val englishBodyPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
                color = inkColor
                textSize = 10.5f
                typeface = bodyTypeface
            }
            val urduBodyPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
                color = inkColor
                textSize = 11.5f
                typeface = urduTypeface
            }
            val footerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = mutedInk
                textSize = 8.5f
                typeface = bodyTypeface
            }
            val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = dividerColor
                strokeWidth = 1f
            }

            var pageNumber = 1
            var currentPage = pdf.startPage(PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageNumber).create())
            var canvas = currentPage.canvas
            var cursorY = drawFirstPageBanner(
                canvas = canvas,
                subject = subject,
                chapter = chapter,
                exportType = exportType,
                titlePaint = titlePaint,
                subHeaderPaint = subHeaderPaint,
                fillPaint = fillPaint
            )

            fun finishAndStartNextPage() {
                drawPageFooter(canvas, pageNumber, subject.name, chapter.title, footerPaint, linePaint)
                pdf.finishPage(currentPage)
                pageNumber++
                currentPage = pdf.startPage(PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageNumber).create())
                canvas = currentPage.canvas
                // Compact running header on subsequent pages
                fillPaint.color = tintBg
                canvas.drawRect(0f, 0f, PAGE_WIDTH.toFloat(), 36f, fillPaint)
                canvas.drawText(
                    "FBISE Class 9 · ${subject.name} · Ch ${chapter.chapterNumber}: ${chapter.title.take(45)}",
                    MARGIN_H,
                    23f,
                    footerPaint
                )
                canvas.drawLine(MARGIN_H, 36f, PAGE_WIDTH - MARGIN_H, 36f, linePaint)
                cursorY = MARGIN_TOP
            }

            for ((heading, bodyText) in sections) {
                if (bodyText.isBlank()) continue

                // Ensure heading doesn't orphan at very bottom
                if (cursorY > PAGE_HEIGHT - MARGIN_BOTTOM - 64f) {
                    finishAndStartNextPage()
                }

                // Draw section heading with amber accent strip
                fillPaint.color = amberColor
                canvas.drawRect(MARGIN_H, cursorY + 2f, MARGIN_H + 4f, cursorY + 18f, fillPaint)
                val headingLayout = buildStaticLayout(heading, sectionPaint, CONTENT_WIDTH - 12, false)
                canvas.save()
                canvas.translate(MARGIN_H + 10f, cursorY)
                headingLayout.draw(canvas)
                canvas.restore()
                cursorY += headingLayout.height + 8f
                canvas.drawLine(MARGIN_H, cursorY - 3f, PAGE_WIDTH - MARGIN_H, cursorY - 3f, linePaint)

                // Render paragraph by paragraph with automatic Urdu RTL detection
                val paragraphs = bodyText.split("\n")
                for (rawPara in paragraphs) {
                    val para = rawPara.trimEnd()
                    if (para.isBlank()) {
                        cursorY += 7f
                        if (cursorY > PAGE_HEIGHT - MARGIN_BOTTOM - 20f) {
                            finishAndStartNextPage()
                        }
                        continue
                    }

                    val isRtl = containsUrduScript(para)
                    val paint = if (isRtl) urduBodyPaint else englishBodyPaint
                    val layout = buildStaticLayout(para, paint, CONTENT_WIDTH, isRtl)

                    if (cursorY + layout.height > PAGE_HEIGHT - MARGIN_BOTTOM) {
                        if (layout.height <= (PAGE_HEIGHT - MARGIN_TOP - MARGIN_BOTTOM)) {
                            finishAndStartNextPage()
                            canvas.save()
                            canvas.translate(MARGIN_H, cursorY)
                            layout.draw(canvas)
                            canvas.restore()
                            cursorY += layout.height + 5f
                        } else {
                            // Split oversized paragraph into lines cleanly
                            for (lineIdx in 0 until layout.lineCount) {
                                val lineStart = layout.getLineStart(lineIdx)
                                val lineEnd = layout.getLineEnd(lineIdx)
                                val lineText = para.substring(lineStart, lineEnd)
                                val singleLayout = buildStaticLayout(lineText, paint, CONTENT_WIDTH, isRtl)
                                if (cursorY + singleLayout.height > PAGE_HEIGHT - MARGIN_BOTTOM) {
                                    finishAndStartNextPage()
                                }
                                canvas.save()
                                canvas.translate(MARGIN_H, cursorY)
                                singleLayout.draw(canvas)
                                canvas.restore()
                                cursorY += singleLayout.height + 2f
                            }
                            cursorY += 4f
                        }
                    } else {
                        canvas.save()
                        canvas.translate(MARGIN_H, cursorY)
                        layout.draw(canvas)
                        canvas.restore()
                        cursorY += layout.height + 5f
                    }
                }
                cursorY += 12f
            }

            drawPageFooter(canvas, pageNumber, subject.name, chapter.title, footerPaint, linePaint)
            pdf.finishPage(currentPage)
            pagesMetadata.add("ok")

            val exportDir = File(context.cacheDir, "exports").apply { mkdirs() }
            val slugSub = subject.name.lowercase(Locale.US).replace(Regex("[^a-z0-9]+"), "_").take(24)
            val fileName = "${slugSub}_ch${chapter.chapterNumber}_${exportType.fileSlug}.pdf"
            val outFile = File(exportDir, fileName)
            FileOutputStream(outFile).use { fos ->
                pdf.writeTo(fos)
            }
            pdf.close()

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                outFile
            )
            Result.success(ExportedPdfResult(outFile, uri, pageNumber, exportType))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun exportBackupJsonFile(context: Context, jsonContent: String): Result<Uri> =
        withContext(Dispatchers.IO) {
            try {
                val exportDir = File(context.cacheDir, "exports").apply { mkdirs() }
                val stamp = SimpleDateFormat("yyyyMMdd_HHmm", Locale.US).format(Date())
                val outFile = File(exportDir, "class9_portal_backup_$stamp.json")
                outFile.writeText(jsonContent, Charsets.UTF_8)
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    outFile
                )
                Result.success(uri)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    private fun drawFirstPageBanner(
        canvas: android.graphics.Canvas,
        subject: Subject,
        chapter: Chapter,
        exportType: PdfExportType,
        titlePaint: TextPaint,
        subHeaderPaint: TextPaint,
        fillPaint: Paint
    ): Float {
        val bannerRect = RectF(0f, 0f, PAGE_WIDTH.toFloat(), 112f)
        fillPaint.color = emeraldColor
        canvas.drawRect(bannerRect, fillPaint)

        // Burnished gold bottom accent rule
        fillPaint.color = amberColor
        canvas.drawRect(0f, 108f, PAGE_WIDTH.toFloat(), 112f, fillPaint)

        val dateStr = SimpleDateFormat("dd MMM yyyy", Locale.US).format(Date())
        canvas.drawText(
            "MY CLASS 9 LEARNING PORTAL · FBISE PAKISTAN (SESSION 2027–2028) · $dateStr",
            MARGIN_H,
            30f,
            subHeaderPaint
        )
        canvas.drawText(
            "${subject.name} — ${exportType.title}".take(58),
            MARGIN_H,
            58f,
            titlePaint
        )
        val chLine = buildString {
            append("Chapter ${chapter.chapterNumber}: ${chapter.title}")
            if (chapter.pageReference.isNotBlank()) append(" (${chapter.pageReference})")
        }
        canvas.drawText(chLine.take(78), MARGIN_H, 82f, subHeaderPaint)
        val sourceLine = chapter.sourceBasis.ifBlank {
            if (subject.curriculumVerified) "Aligned with uploaded 2027-2028 FBISE materials"
            else "Study Guide — Verify against official 2027-2028 FBISE syllabus"
        }
        canvas.drawText("Source: ${sourceLine.take(76)}", MARGIN_H, 98f, subHeaderPaint)
        return 128f
    }

    private fun drawPageFooter(
        canvas: android.graphics.Canvas,
        pageNumber: Int,
        subjectName: String,
        chapterTitle: String,
        footerPaint: Paint,
        linePaint: Paint
    ) {
        val y = PAGE_HEIGHT - 30f
        canvas.drawLine(MARGIN_H, y - 12f, PAGE_WIDTH - MARGIN_H, y - 12f, linePaint)
        canvas.drawText(
            "Class 9 Learning Portal · $subjectName · ${chapterTitle.take(40)}",
            MARGIN_H,
            y,
            footerPaint
        )
        val pageLabel = "Page $pageNumber"
        val width = footerPaint.measureText(pageLabel)
        canvas.drawText(pageLabel, PAGE_WIDTH - MARGIN_H - width, y, footerPaint)
    }

    private fun buildStaticLayout(
        text: String,
        paint: TextPaint,
        width: Int,
        isRtl: Boolean
    ): StaticLayout {
        val builder = StaticLayout.Builder.obtain(text, 0, text.length, paint, width)
            .setAlignment(if (isRtl) Layout.Alignment.ALIGN_OPPOSITE else Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(2f, if (isRtl) 1.35f else 1.18f)
            .setIncludePad(false)
            .setTextDirection(
                if (isRtl) TextDirectionHeuristics.RTL else TextDirectionHeuristics.FIRSTSTRONG_LTR
            )
        return builder.build()
    }

    private fun containsUrduScript(text: String): Boolean {
        var urduChars = 0
        var latinChars = 0
        for (ch in text) {
            val code = ch.code
            if (code in 0x0600..0x06FF || code in 0x0750..0x077F || code in 0xFB50..0xFDFF || code in 0xFE70..0xFEFF) {
                urduChars++
            } else if (ch in 'A'..'Z' || ch in 'a'..'z') {
                latinChars++
            }
        }
        return urduChars > 0 && urduChars >= latinChars / 2
    }

    private fun buildSections(
        subject: Subject,
        chapter: Chapter,
        mcqBank: McqBank?,
        exportType: PdfExportType
    ): List<Pair<String, String>> {
        val introBlock = buildString {
            if (chapter.introduction.isNotBlank()) appendLine(chapter.introduction)
            if (chapter.urduTitle.isNotBlank()) appendLine(chapter.urduTitle)
            if (chapter.urduIntroduction.isNotBlank()) appendLine(chapter.urduIntroduction)
            if (chapter.topics.isNotEmpty()) {
                appendLine("\nTopics Covered:")
                chapter.topics.forEachIndexed { idx, t -> appendLine("${idx + 1}. $t") }
            }
            if (chapter.officialSlos.isNotEmpty()) {
                appendLine("\nOfficial SLOs:")
                chapter.officialSlos.forEach { appendLine("• $it") }
            }
        }

        val mcqsWithAnswersText = buildString {
            if (mcqBank == null || mcqBank.questions.isEmpty()) {
                appendLine("No MCQ bank generated yet for this chapter. Open the MCQs & Tests tab to generate 30+ chapter MCQs first.")
            } else {
                mcqBank.questions.forEach { q ->
                    val badge = if (q.sourceType == "VERIFIED_OFFICIAL") "[Official]" else "[Practice]"
                    appendLine("Q${q.number}. ${q.question} $badge (${q.pageRef.ifBlank { q.topicTag }})")
                    if (q.urduHint.isNotBlank()) appendLine(q.urduHint)
                    val letters = listOf("A", "B", "C", "D")
                    q.options.forEachIndexed { i, opt ->
                        appendLine("   ${letters.getOrElse(i) { "?" }}) $opt")
                    }
                    val correctLetter = letters.getOrElse(q.correctIndex) { "A" }
                    appendLine("   Correct Answer: $correctLetter — ${q.explanation}")
                    appendLine()
                }
            }
        }

        val chapterTestText = buildString {
            appendLine("Time Allowed: 45 Minutes    |    Maximum Marks: 50    |    Class 9 (${subject.name})")
            appendLine("Note: Answer all objective questions. Attempt short and long questions neatly with units & diagrams.\n")
            appendLine("SECTION A — MULTIPLE CHOICE QUESTIONS (Objective)")
            if (mcqBank == null || mcqBank.questions.isEmpty()) {
                appendLine("Generate the chapter MCQ bank first to populate the objective test paper.")
            } else {
                mcqBank.questions.take(20).forEach { q ->
                    appendLine("${q.number}. ${q.question}")
                    val letters = listOf("A", "B", "C", "D")
                    q.options.forEachIndexed { i, opt ->
                        append("   (${letters.getOrElse(i) { "?" }}) $opt   ")
                    }
                    appendLine("\n")
                }
                if (mcqBank.shortTestQuestions.isNotEmpty()) {
                    appendLine("\nSECTION B — SHORT QUESTIONS")
                    mcqBank.shortTestQuestions.forEachIndexed { idx, sq ->
                        appendLine("${idx + 1}. $sq")
                    }
                }
                if (mcqBank.longTestQuestions.isNotEmpty()) {
                    appendLine("\nSECTION C — EXTENSIVE / LONG QUESTIONS")
                    mcqBank.longTestQuestions.forEachIndexed { idx, lq ->
                        appendLine("${idx + 1}. $lq")
                    }
                }
            }
        }

        val answerKeyText = buildString {
            if (mcqBank == null || mcqBank.questions.isEmpty()) {
                appendLine("No MCQ/Test bank generated yet.")
            } else {
                val letters = listOf("A", "B", "C", "D")
                appendLine("OBJECTIVE MCQ ANSWER KEY:")
                mcqBank.questions.forEach { q ->
                    appendLine("Q${q.number}: ${letters.getOrElse(q.correctIndex) { "A" }} (${q.options.getOrElse(q.correctIndex) { "" }})")
                }
                if (mcqBank.answerKeySummary.isNotBlank()) {
                    appendLine("\nDETAILED MARKING & ANSWER KEY:")
                    appendLine(mcqBank.answerKeySummary)
                }
            }
        }

        return when (exportType) {
            PdfExportType.COMPLETE_NOTES -> listOf(
                "Chapter Overview & Topics" to introBlock,
                "Complete Detailed Notes" to chapter.completeNotes.ifBlank { "No detailed notes generated yet." },
                "Formulas, Equations & Key Concepts" to chapter.formulasAndConcepts,
                "Solved Examples" to chapter.solvedExamples,
                "Chapter Summary" to chapter.chapterSummary
            )
            PdfExportType.SHORT_NOTES -> listOf(
                "Quick Revision Notes" to chapter.shortNotes.ifBlank { "No short revision notes generated yet." },
                "Key Formulas & Concepts" to chapter.formulasAndConcepts,
                "Chapter Summary" to chapter.chapterSummary
            )
            PdfExportType.SOLVED_EXERCISES -> listOf(
                "Solved Textbook Exercises" to chapter.solvedExercises.ifBlank { "No solved exercises generated yet." },
                "Solved Examples (Step-by-Step)" to chapter.solvedExamples
            )
            PdfExportType.DEFINITIONS -> listOf(
                "Definitions & Bilingual Terminology" to chapter.definitions.ifBlank { "No definitions generated yet." }
            )
            PdfExportType.IMPORTANT_QUESTIONS -> listOf(
                "Important Examination Questions (Short, Long, Conceptual & Application)" to
                    chapter.importantQuestions.ifBlank { "No important questions generated yet." }
            )
            PdfExportType.MCQS_WITH_ANSWERS -> listOf(
                "Chapter MCQs with Verified Explanations" to mcqsWithAnswersText
            )
            PdfExportType.CHAPTER_TEST -> listOf(
                "FBISE Class 9 Chapter Test Paper" to chapterTestText
            )
            PdfExportType.ANSWER_KEY -> listOf(
                "Official-Style Answer Key & Marking Guide" to answerKeyText
            )
            PdfExportType.FULL_CHAPTER_PACKAGE -> listOf(
                "1. Chapter Overview, Topics & SLOs" to introBlock,
                "2. Complete Detailed Notes" to chapter.completeNotes.ifBlank { "Generate notes in the Chapter Workspace." },
                "3. Short Revision Notes" to chapter.shortNotes,
                "4. Definitions & Bilingual Terminology" to chapter.definitions,
                "5. Formulas, Equations & Key Concepts" to chapter.formulasAndConcepts,
                "6. Solved Examples" to chapter.solvedExamples,
                "7. Solved Textbook Exercises" to chapter.solvedExercises,
                "8. Important Examination Questions" to chapter.importantQuestions,
                "9. Chapter Summary" to chapter.chapterSummary,
                "10. Chapter MCQs with Answers" to mcqsWithAnswersText,
                "11. Chapter Test & Separate Answer Key" to "$chapterTestText\n\n$answerKeyText"
            )
        }
    }
}
