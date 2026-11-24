package com.example.data.ai

import android.graphics.Bitmap
import android.util.Log
import com.example.data.model.Chapter
import com.example.data.model.McqQuestion
import com.example.data.model.StudyMaterial
import com.example.data.model.Subject
import com.example.data.model.TutorMessage
import com.example.data.model.TutorRole
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import org.json.JSONObject

data class ExtractedChapterStub(
    val chapterNumber: Int,
    val title: String,
    val urduTitle: String,
    val pageReference: String,
    val introduction: String,
    val topics: List<String>,
    val officialSlos: List<String>
)

data class MaterialAnalysisResult(
    val sessionYearDetected: String,
    val verified20272028: Boolean,
    val curriculumWarning: String,
    val extractedSummary: String,
    val extractedKeyText: String,
    val unreadablePagesReport: String,
    val chapters: List<ExtractedChapterStub>
)

data class GeneratedNotesPackage(
    val introduction: String,
    val urduIntroduction: String,
    val learningObjectives: List<String>,
    val completeNotes: String,
    val shortNotes: String,
    val definitions: String,
    val formulasAndConcepts: String,
    val solvedExamples: String,
    val solvedExercises: String,
    val importantQuestions: String,
    val chapterSummary: String,
    val sourceBasis: String
)

data class GeneratedMcqPackage(
    val questions: List<McqQuestion>,
    val shortTestQuestions: List<String>,
    val longTestQuestions: List<String>,
    val answerKeySummary: String
)

class GeminiAcademicService {

    companion object {
        private const val TAG = "GeminiAcademicService"
        private const val DEFAULT_MODEL = "gemini-3.8-flash"
    }

    private val generalModel by lazy {
        Firebase.ai.generativeModel(modelName = DEFAULT_MODEL)
    }

    private val materialAnalysisModel by lazy {
        val chapterSchema = Schema.obj(
            properties = mapOf(
                "chapterNumber" to Schema.integer("Exact chapter number in textbook order starting from 1"),
                "title" to Schema.string("Chapter title in English (or Urdu for Urdu subject)"),
                "urduTitle" to Schema.string("Urdu translation of the chapter title in Urdu script"),
                "pageReference" to Schema.string("Page range such as 'pp. 1-24' or 'Not specified'"),
                "introduction" to Schema.string("Concise 2-3 sentence introduction grounded in the uploaded document"),
                "topics" to Schema.array(Schema.string(), "Ordered list of topics and subtopics found"),
                "officialSlos" to Schema.array(Schema.string(), "ONLY verbatim SLOs explicitly written in the uploaded document; empty array if none are present. NEVER invent SLO codes.")
            )
        )
        val schema = Schema.obj(
            properties = mapOf(
                "sessionYearDetected" to Schema.string("Detected academic/exam session e.g. '2027-2028', '2025-2026', or 'Not specified'"),
                "verified20272028" to Schema.boolean("True ONLY if document explicitly states 2027-2028 or 2027/2028 FBISE session"),
                "curriculumWarning" to Schema.string("Empty if verified for 2027-2028; otherwise clear warning naming the detected version"),
                "extractedSummary" to Schema.string("Comprehensive summary of the document contents, structure, formulas, and key questions"),
                "extractedKeyText" to Schema.string("Detailed OCR/extracted educational text preserving headings, definitions, formulas, and exercise questions"),
                "unreadablePagesReport" to Schema.string("Explicit note of any blurry, cut-off, or unreadable pages; empty if all inspected pages are legible"),
                "chapters" to Schema.array(chapterSchema, "Chapters identified in original textbook order")
            )
        )
        Firebase.ai.generativeModel(
            modelName = DEFAULT_MODEL,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = schema
            }
        )
    }

    private val notesModel by lazy {
        val schema = Schema.obj(
            properties = mapOf(
                "introduction" to Schema.string("Clear chapter introduction in simple English"),
                "urduIntroduction" to Schema.string("Natural, easy Urdu explanation of the chapter introduction in Urdu script"),
                "learningObjectives" to Schema.array(Schema.string(), "Clear learning objectives for the student"),
                "completeNotes" to Schema.string("Comprehensive detailed notes organized by topic/subtopic with English + easy Urdu explanations, terminology, practical examples, and page references where available"),
                "shortNotes" to Schema.string("High-yield concise bullet-point revision notes for exam preparation"),
                "definitions" to Schema.string("Important terms formatted with English definition, Urdu translation, and easy Urdu explanation"),
                "formulasAndConcepts" to Schema.string("Formulas, SI units, balanced chemical equations, laws, algorithms/flowcharts, or key concepts tailored to the subject"),
                "solvedExamples" to Schema.string("Worked examples; for numericals strictly use: Given, Required, Formula, Substitution, Calculation, Final Answer"),
                "solvedExercises" to Schema.string("Complete solved textbook exercise questions preserving original numbering, calculation steps, and Urdu notes for difficult concepts"),
                "importantQuestions" to Schema.string("Categorized Important Short Questions, Long Questions, Conceptual Questions, and Application-Based Questions. Clearly label [Verified Official] ONLY if from uploaded official past/model papers, otherwise label [Practice Question]"),
                "chapterSummary" to Schema.string("Bilingual English and Urdu chapter summary"),
                "sourceBasis" to Schema.string("Clear attribution noting which uploaded materials were used or whether this is an AI Practice Study Guide pending official textbook upload")
            )
        )
        Firebase.ai.generativeModel(
            modelName = DEFAULT_MODEL,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = schema
            }
        )
    }

    private val mcqModel by lazy {
        val mcqSchema = Schema.obj(
            properties = mapOf(
                "number" to Schema.integer("Question number 1 to 30"),
                "question" to Schema.string("Clear FBISE Class 9 MCQ stem"),
                "urduHint" to Schema.string("Short helpful Urdu translation/hint in Urdu script"),
                "options" to Schema.array(Schema.string(), "Exactly 4 distinct options A, B, C, D"),
                "correctIndex" to Schema.integer("Zero-based index 0, 1, 2, or 3"),
                "explanation" to Schema.string("Step-by-step reason why the correct option is right"),
                "topicTag" to Schema.string("Short topic name within the chapter for weak-topic analytics"),
                "sourceType" to Schema.enumeration(listOf("VERIFIED_OFFICIAL", "AI_PRACTICE"), "Use VERIFIED_OFFICIAL ONLY if copied verbatim from an uploaded official past/model paper, else AI_PRACTICE"),
                "pageRef" to Schema.string("Textbook page reference if known, else 'General'")
            )
        )
        val schema = Schema.obj(
            properties = mapOf(
                "questions" to Schema.array(mcqSchema, "At least 30 well-crafted MCQs covering all chapter topics"),
                "shortTestQuestions" to Schema.array(Schema.string(), "10 FBISE-style Short Questions for the chapter test"),
                "longTestQuestions" to Schema.array(Schema.string(), "4 FBISE-style Detailed/Long Questions (with numericals/parts where relevant)"),
                "answerKeySummary" to Schema.string("Complete separate Answer Key for all 30 MCQs, Short Questions, and Long Questions")
            )
        )
        Firebase.ai.generativeModel(
            modelName = DEFAULT_MODEL,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = schema
            }
        )
    }

    suspend fun analyzeUploadedMaterial(
        subject: Subject,
        fileName: String,
        categoryLabel: String,
        extractedPlainText: String,
        bitmaps: List<Bitmap>,
        localUnreadableNote: String
    ): Result<MaterialAnalysisResult> {
        return try {
            val prompt = buildString {
                appendLine("You are an expert Pakistani FBISE Class 9 academic archivist and OCR analyst.")
                appendLine("Subject: ${subject.name} (${subject.code})")
                appendLine("Uploaded File: $fileName | Category: $categoryLabel")
                appendLine("Target Examination Session: FBISE Pakistan 2027-2028.")
                appendLine("STRICT ACADEMIC INTEGRITY RULES:")
                appendLine("1. Extract chapters, topics, formulas, diagrams descriptions, questions, and page references accurately in original textbook order.")
                appendLine("2. Check if the document explicitly applies to the 2027-2028 session. If older or unspecified, set verified20272028=false and state the detected version in curriculumWarning.")
                appendLine("3. NEVER invent official FBISE SLO codes. Only list SLOs explicitly present in the provided pages/text.")
                appendLine("4. NEVER silently ignore blurry, blank, or unreadable pages — explicitly note them in unreadablePagesReport.")
                if (localUnreadableNote.isNotBlank()) {
                    appendLine("Local Reader Diagnostic: $localUnreadableNote")
                }
                if (extractedPlainText.isNotBlank()) {
                    appendLine("\n--- EXTRACTED DOCUMENT TEXT (truncated to fit context) ---")
                    appendLine(extractedPlainText.take(28000))
                }
            }

            val response = withTimeout(90_000L) {
                if (bitmaps.isNotEmpty()) {
                    materialAnalysisModel.generateContent(
                        content {
                            text(prompt)
                            bitmaps.take(6).forEach { bmp -> image(bmp) }
                        }
                    )
                } else {
                    materialAnalysisModel.generateContent(prompt)
                }
            }

            val rawJson = response.text ?: throw IllegalStateException("Gemini returned an empty analysis response.")
            val json = JSONObject(rawJson)
            val chaptersArr = json.optJSONArray("chapters")
            val chapters = mutableListOf<ExtractedChapterStub>()
            if (chaptersArr != null) {
                for (i in 0 until chaptersArr.length()) {
                    val obj = chaptersArr.getJSONObject(i)
                    val topicsArr = obj.optJSONArray("topics")
                    val slosArr = obj.optJSONArray("officialSlos")
                    val topics = buildList {
                        if (topicsArr != null) {
                            for (j in 0 until topicsArr.length()) {
                                topicsArr.optString(j).takeIf { it.isNotBlank() }?.let { add(it) }
                            }
                        }
                    }
                    val slos = buildList {
                        if (slosArr != null) {
                            for (j in 0 until slosArr.length()) {
                                slosArr.optString(j).takeIf { it.isNotBlank() }?.let { add(it) }
                            }
                        }
                    }
                    chapters.add(
                        ExtractedChapterStub(
                            chapterNumber = obj.optInt("chapterNumber", i + 1).coerceAtLeast(1),
                            title = obj.optString("title", "Chapter ${i + 1}"),
                            urduTitle = obj.optString("urduTitle", ""),
                            pageReference = obj.optString("pageReference", ""),
                            introduction = obj.optString("introduction", ""),
                            topics = topics,
                            officialSlos = slos
                        )
                    )
                }
            }

            val combinedUnreadable = listOf(localUnreadableNote, json.optString("unreadablePagesReport", ""))
                .filter { it.isNotBlank() }
                .joinToString(" ")

            Result.success(
                MaterialAnalysisResult(
                    sessionYearDetected = json.optString("sessionYearDetected", "Unverified"),
                    verified20272028 = json.optBoolean("verified20272028", false),
                    curriculumWarning = json.optString("curriculumWarning", ""),
                    extractedSummary = json.optString("extractedSummary", ""),
                    extractedKeyText = json.optString("extractedKeyText", extractedPlainText.take(20000)),
                    unreadablePagesReport = combinedUnreadable,
                    chapters = chapters
                )
            )
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Document analysis timed out", e)
            Result.failure(IllegalStateException("Document analysis timed out. Try a smaller file or chapter excerpt."))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Document analysis failed", e)
            Result.failure(e)
        }
    }

    suspend fun generateChapterNotesPackage(
        subject: Subject,
        chapter: Chapter,
        subjectMaterials: List<StudyMaterial>,
        urduEnabled: Boolean
    ): Result<GeneratedNotesPackage> {
        return try {
            val materialContext = buildMaterialContext(subjectMaterials)
            val prompt = buildString {
                appendLine("You are an expert Pakistani FBISE Class 9 teacher preparing a complete bilingual study guide for the 2027-2028 examination session.")
                appendLine("Subject: ${subject.name} (${subject.code})")
                appendLine("Language Mode: ${subject.languageModeEnum.label} | Urdu Explanations Enabled: $urduEnabled")
                appendLine("Subject-Specific Teaching Instructions: ${subject.teachingStrategy}")
                appendLine("Chapter ${chapter.chapterNumber}: ${chapter.title}")
                if (chapter.urduTitle.isNotBlank()) appendLine("Urdu Title: ${chapter.urduTitle}")
                if (chapter.pageReference.isNotBlank()) appendLine("Page Reference: ${chapter.pageReference}")
                if (chapter.topics.isNotEmpty()) appendLine("Topics: ${chapter.topics.joinToString(", ")}")
                if (chapter.officialSlos.isNotEmpty()) appendLine("Official SLOs: ${chapter.officialSlos.joinToString(" | ")}")
                appendLine("\nSTRICT REQUIREMENTS:")
                appendLine("1. Ground notes FIRST in the uploaded subject materials below. Cite textbook page numbers wherever available.")
                appendLine("2. If no official uploaded textbook excerpt is provided for a sub-section, clearly note in sourceBasis that it is a Practice Study Guide pending official textbook upload.")
                appendLine("3. NEVER invent official FBISE SLO codes or claim generated practice questions are actual past-paper questions.")
                appendLine("4. Preserve scientific formulas, chemical equations, SI units, and mathematical notation accurately.")
                appendLine("5. For numerical examples/exercises ALWAYS format with: Given, Required, Formula, Substitution, Calculation, Final Answer.")
                appendLine("6. Preserve original textbook exercise question numbering where available.")
                if (urduEnabled) {
                    appendLine("7. Include clear, accurate Urdu script translations and easy Urdu explanations for difficult concepts and definitions.")
                }
                appendLine("\n--- UPLOADED SUBJECT MATERIALS ---")
                appendLine(materialContext)
            }

            val response = withTimeout(90_000L) {
                notesModel.generateContent(prompt)
            }
            val rawJson = response.text ?: throw IllegalStateException("Gemini returned empty notes response.")
            val json = JSONObject(rawJson)
            val objArr = json.optJSONArray("learningObjectives")
            val objectives = buildList {
                if (objArr != null) {
                    for (i in 0 until objArr.length()) {
                        objArr.optString(i).takeIf { it.isNotBlank() }?.let { add(it) }
                    }
                }
            }

            Result.success(
                GeneratedNotesPackage(
                    introduction = json.optString("introduction", ""),
                    urduIntroduction = json.optString("urduIntroduction", ""),
                    learningObjectives = objectives,
                    completeNotes = json.optString("completeNotes", ""),
                    shortNotes = json.optString("shortNotes", ""),
                    definitions = json.optString("definitions", ""),
                    formulasAndConcepts = json.optString("formulasAndConcepts", ""),
                    solvedExamples = json.optString("solvedExamples", ""),
                    solvedExercises = json.optString("solvedExercises", ""),
                    importantQuestions = json.optString("importantQuestions", ""),
                    chapterSummary = json.optString("chapterSummary", ""),
                    sourceBasis = json.optString("sourceBasis", "AI Study Guide")
                )
            )
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Notes generation timed out", e)
            Result.failure(IllegalStateException("Notes generation timed out after 90 seconds. Please retry."))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Notes generation failed", e)
            Result.failure(e)
        }
    }

    suspend fun generateMcqAndTestBank(
        subject: Subject,
        chapter: Chapter,
        subjectMaterials: List<StudyMaterial>
    ): Result<GeneratedMcqPackage> {
        return try {
            val materialContext = buildMaterialContext(subjectMaterials)
            val prompt = buildString {
                appendLine("You are an FBISE Pakistan Class 9 examination paper setter for the 2027-2028 session.")
                appendLine("Subject: ${subject.name} (${subject.code})")
                appendLine("Chapter ${chapter.chapterNumber}: ${chapter.title}")
                appendLine("Teaching Strategy: ${subject.teachingStrategy}")
                if (chapter.topics.isNotEmpty()) appendLine("Topics: ${chapter.topics.joinToString(", ")}")
                if (chapter.completeNotes.isNotBlank()) {
                    appendLine("Chapter Notes Context:\n${chapter.completeNotes.take(6000)}")
                }
                appendLine("\nUPLOADED SUBJECT MATERIALS:")
                appendLine(materialContext)
                appendLine("\nREQUIREMENTS:")
                appendLine("1. Generate EXACTLY 30 distinct, high-quality MCQs covering every topic in this chapter.")
                appendLine("2. Each MCQ MUST have exactly 4 answer options, correctIndex (0..3), clear explanation, short Urdu hint in Urdu script, topicTag, and pageRef.")
                appendLine("3. Mark sourceType as VERIFIED_OFFICIAL ONLY if copied verbatim from an uploaded official past/model paper; otherwise ALWAYS use AI_PRACTICE.")
                appendLine("4. Also provide 10 FBISE-style Short Test Questions, 4 Long Test Questions, and a complete separate Answer Key Summary.")
            }

            val response = withTimeout(90_000L) {
                mcqModel.generateContent(prompt)
            }
            val rawJson = response.text ?: throw IllegalStateException("Gemini returned empty MCQ bank.")
            val json = JSONObject(rawJson)
            val qArr = json.optJSONArray("questions")
            val questions = mutableListOf<McqQuestion>()
            if (qArr != null) {
                for (i in 0 until qArr.length()) {
                    val obj = qArr.getJSONObject(i)
                    val optArr = obj.optJSONArray("options")
                    val options = mutableListOf<String>()
                    if (optArr != null) {
                        for (j in 0 until optArr.length()) {
                            options.add(optArr.optString(j, ""))
                        }
                    }
                    while (options.size < 4) options.add("Option ${'A' + options.size}")
                    questions.add(
                        McqQuestion(
                            number = obj.optInt("number", i + 1),
                            question = obj.optString("question", ""),
                            urduHint = obj.optString("urduHint", ""),
                            options = options.take(4),
                            correctIndex = obj.optInt("correctIndex", 0).coerceIn(0, 3),
                            explanation = obj.optString("explanation", ""),
                            topicTag = obj.optString("topicTag", chapter.title),
                            sourceType = if (obj.optString("sourceType") == "VERIFIED_OFFICIAL") "VERIFIED_OFFICIAL" else "AI_PRACTICE",
                            pageRef = obj.optString("pageRef", "")
                        )
                    )
                }
            }

            val shortArr = json.optJSONArray("shortTestQuestions")
            val shortQs = buildList {
                if (shortArr != null) {
                    for (i in 0 until shortArr.length()) {
                        shortArr.optString(i).takeIf { it.isNotBlank() }?.let { add(it) }
                    }
                }
            }
            val longArr = json.optJSONArray("longTestQuestions")
            val longQs = buildList {
                if (longArr != null) {
                    for (i in 0 until longArr.length()) {
                        longArr.optString(i).takeIf { it.isNotBlank() }?.let { add(it) }
                    }
                }
            }

            Result.success(
                GeneratedMcqPackage(
                    questions = questions,
                    shortTestQuestions = shortQs,
                    longTestQuestions = longQs,
                    answerKeySummary = json.optString("answerKeySummary", "")
                )
            )
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "MCQ generation timed out", e)
            Result.failure(IllegalStateException("MCQ test generation timed out. Please try again."))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "MCQ generation failed", e)
            Result.failure(e)
        }
    }

    fun streamTutorResponse(
        subject: Subject,
        chapter: Chapter?,
        subjectMaterials: List<StudyMaterial>,
        history: List<TutorMessage>,
        userQuestion: String,
        urduEnabled: Boolean
    ): Flow<String> = flow {
        val materialContext = buildMaterialContext(subjectMaterials, maxChars = 14000)
        val systemPrompt = buildString {
            appendLine("You are the dedicated FBISE Pakistan Class 9 (2027-2028) Personal AI Tutor for ONE student.")
            appendLine("Active Subject ONLY: ${subject.name} (${subject.code}). NEVER mix context from other subjects.")
            appendLine("Subject Teaching Strategy: ${subject.teachingStrategy}")
            appendLine("Language Mode: ${subject.languageModeEnum.label} | Include Urdu Explanations when helpful: $urduEnabled")
            if (chapter != null) {
                appendLine("Focused Chapter: Chapter ${chapter.chapterNumber} - ${chapter.title}")
                if (chapter.topics.isNotEmpty()) appendLine("Chapter Topics: ${chapter.topics.joinToString(", ")}")
                if (chapter.completeNotes.isNotBlank()) {
                    appendLine("Chapter Notes Excerpt:\n${chapter.completeNotes.take(4500)}")
                }
            }
            appendLine("\nUPLOADED SUBJECT MATERIALS (PRIMARY SOURCE):")
            appendLine(materialContext)
            appendLine("\nSTRICT TUTOR RULES:")
            appendLine("1. Always prioritize uploaded subject materials first and mention the file name / page reference where available.")
            appendLine("2. Never claim an AI explanation is an official textbook answer unless verified directly against the uploaded materials.")
            appendLine("3. Keep scientific symbols, chemical equations, SI units, and mathematical notation crystal clear.")
            appendLine("4. For numericals, use Given -> Required -> Formula -> Substitution -> Calculation -> Final Answer.")
            appendLine("5. If the student asks for Urdu translation or if a concept is difficult, include a clearly separated Urdu explanation under a heading `اردو وضاحت:`.")
            appendLine("\nRECENT CONVERSATION IN ${subject.name}:")
            history.takeLast(8).forEach { msg ->
                val speaker = if (msg.roleEnum == TutorRole.USER) "Student" else "Tutor"
                appendLine("$speaker: ${msg.content.take(800)}")
            }
            appendLine("\nStudent Question: $userQuestion")
        }

        val accumulated = StringBuilder()
        withTimeout(60_000L) {
            generalModel.generateContentStream(systemPrompt).collect { chunk ->
                val piece = chunk.text.orEmpty()
                if (piece.isNotEmpty()) {
                    accumulated.append(piece)
                    emit(accumulated.toString())
                }
            }
        }
    }

    suspend fun generateCustomTeachingStrategy(subjectName: String): Result<String> {
        return try {
            val prompt = "Write a concise 3-sentence FBISE Pakistan Class 9 (2027-2028) subject-specific teaching strategy for \"$subjectName\". Specify step-by-step pedagogical structure, notation/diagram/language expectations (English + Urdu support), and examination answer style. Output plain text only."
            val response = withTimeout(30_000L) {
                generalModel.generateContent(prompt)
            }
            val text = response.text?.trim().orEmpty()
            if (text.isBlank()) Result.failure(IllegalStateException("Empty strategy response"))
            else Result.success(text.take(1500))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Strategy generation failed", e)
            Result.failure(e)
        }
    }

    private fun buildMaterialContext(
        materials: List<StudyMaterial>,
        maxChars: Int = 20000
    ): String {
        if (materials.isEmpty()) {
            return "No official textbook or curriculum files have been uploaded for this subject yet. Clearly label outputs as AI Practice Study Material pending official textbook upload."
        }
        val sb = StringBuilder()
        materials.forEach { m ->
            if (sb.length >= maxChars) return@forEach
            sb.appendLine("[Source: ${m.fileName} | Category: ${m.categoryEnum.label} | Session: ${m.sessionYearDetected} | VerifiedOfficial: ${m.verifiedOfficialSource}]")
            if (m.extractedSummary.isNotBlank()) {
                sb.appendLine("Summary: ${m.extractedSummary.take(2500)}")
            }
            if (m.extractedText.isNotBlank()) {
                val remaining = (maxChars - sb.length).coerceAtLeast(0)
                sb.appendLine("Extracted Content:\n${m.extractedText.take(minOf(remaining, 8000))}")
            }
            sb.appendLine("---")
        }
        return sb.toString().take(maxChars)
    }
}
