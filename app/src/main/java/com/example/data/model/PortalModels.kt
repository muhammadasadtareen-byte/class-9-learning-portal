package com.example.data.model

import androidx.annotation.Keep
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

enum class LanguageMode(val label: String) {
    ENGLISH_URDU("English + Urdu Support"),
    URDU_PRIMARY("Urdu Primary (RTL)"),
    ENGLISH_PRIMARY("English Primary");

    companion object {
        fun fromRaw(raw: String): LanguageMode =
            entries.firstOrNull { it.name == raw } ?: ENGLISH_URDU
    }
}

enum class MaterialCategory(val label: String) {
    TEXTBOOK("PDF Textbook"),
    SCANNED_BOOK("Scanned PDF Book"),
    CURRICULUM_SLO("Official Curriculum / SLO"),
    MODEL_PAPER("FBISE Model Paper"),
    PAST_PAPER("Past Paper"),
    MARKING_SCHEME("Marking Scheme"),
    STUDY_NOTES("Study Notes"),
    WORD_DOC("Word / DOCX Document"),
    PAGE_IMAGE("Textbook Page Image");

    companion object {
        fun fromRaw(raw: String): MaterialCategory =
            entries.firstOrNull { it.name == raw } ?: TEXTBOOK
    }
}

enum class ProcessingStatus(val label: String) {
    UPLOADED("Uploaded"),
    PROCESSING("Analyzing…"),
    PROCESSED("Processed"),
    PARTIAL_WARNING("Processed with Warnings"),
    ERROR("Needs Attention");

    companion object {
        fun fromRaw(raw: String): ProcessingStatus =
            entries.firstOrNull { it.name == raw } ?: UPLOADED
    }
}

enum class TutorRole {
    USER,
    TUTOR;

    companion object {
        fun fromRaw(raw: String): TutorRole =
            entries.firstOrNull { it.name == raw } ?: USER
    }
}

@Keep
@IgnoreExtraProperties
data class StudentProfile(
    @DocumentId val id: String = "",
    val userId: String = "",
    val studentName: String = "Class 9 Student",
    val className: String = "Class 9",
    val board: String = "FBISE Pakistan",
    val session: String = "2027-2028",
    val parentModeEnabled: Boolean = true,
    val urduSupportEnabled: Boolean = true,
    val lastStudiedSubjectId: String = "",
    val lastStudiedChapterId: String = "",
    val lastStudiedTopicTitle: String = "",
    val seededDefaults: Boolean = false,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class Subject(
    @DocumentId val id: String = "",
    val userId: String = "",
    val name: String = "",
    val code: String = "",
    val description: String = "",
    val iconKey: String = "menu_book",
    val customIconUri: String = "",
    val accentColorKey: String = "emerald",
    val enabled: Boolean = true,
    val languageMode: String = LanguageMode.ENGLISH_URDU.name,
    val curriculumSession: String = "Pending 2027-2028 Upload",
    val curriculumVerified: Boolean = false,
    val curriculumWarning: String = "",
    val teachingStrategy: String = "",
    val sortOrder: Int = 0,
    val chapterCount: Int = 0,
    val completedChapterCount: Int = 0,
    val materialCount: Int = 0,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
) {
    val languageModeEnum: LanguageMode get() = LanguageMode.fromRaw(languageMode)
    val progressFraction: Float
        get() = if (chapterCount <= 0) 0f else (completedChapterCount.toFloat() / chapterCount.toFloat()).coerceIn(0f, 1f)
}

@Keep
@IgnoreExtraProperties
data class StudyMaterial(
    @DocumentId val id: String = "",
    val userId: String = "",
    val subjectId: String = "",
    val fileName: String = "",
    val mimeType: String = "application/pdf",
    val category: String = MaterialCategory.TEXTBOOK.name,
    val fileSizeBytes: Long = 0L,
    val localUri: String = "",
    val processingStatus: String = ProcessingStatus.UPLOADED.name,
    val statusMessage: String = "",
    val sessionYearDetected: String = "Unverified",
    val verifiedOfficialSource: Boolean = false,
    val extractedSummary: String = "",
    val extractedText: String = "",
    val pageCount: Int = 0,
    val unreadablePagesNote: String = "",
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
) {
    val categoryEnum: MaterialCategory get() = MaterialCategory.fromRaw(category)
    val statusEnum: ProcessingStatus get() = ProcessingStatus.fromRaw(processingStatus)
}

@Keep
@IgnoreExtraProperties
data class Chapter(
    @DocumentId val id: String = "",
    val userId: String = "",
    val subjectId: String = "",
    val chapterNumber: Int = 1,
    val title: String = "",
    val urduTitle: String = "",
    val pageReference: String = "",
    val introduction: String = "",
    val urduIntroduction: String = "",
    val learningObjectives: List<String> = emptyList(),
    val officialSlos: List<String> = emptyList(),
    val topics: List<String> = emptyList(),
    val completeNotes: String = "",
    val shortNotes: String = "",
    val definitions: String = "",
    val formulasAndConcepts: String = "",
    val solvedExamples: String = "",
    val solvedExercises: String = "",
    val importantQuestions: String = "",
    val chapterSummary: String = "",
    val sourceBasis: String = "",
    val completed: Boolean = false,
    val masteryPercent: Int = 0,
    val lastQuizScorePercent: Int = 0,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class McqQuestion(
    val number: Int = 1,
    val question: String = "",
    val urduHint: String = "",
    val options: List<String> = listOf("", "", "", ""),
    val correctIndex: Int = 0,
    val explanation: String = "",
    val topicTag: String = "",
    val sourceType: String = "AI_PRACTICE", // VERIFIED_OFFICIAL or AI_PRACTICE
    val pageRef: String = ""
) {
    fun toMap(): Map<String, Any> = mapOf(
        "number" to number,
        "question" to question,
        "urduHint" to urduHint,
        "options" to options.take(4),
        "correctIndex" to correctIndex.coerceIn(0, 3),
        "explanation" to explanation,
        "topicTag" to topicTag,
        "sourceType" to sourceType,
        "pageRef" to pageRef
    )
}

@Keep
@IgnoreExtraProperties
data class McqBank(
    @DocumentId val id: String = "",
    val userId: String = "",
    val subjectId: String = "",
    val chapterId: String = "",
    val chapterTitle: String = "",
    val questions: List<McqQuestion> = emptyList(),
    val shortTestQuestions: List<String> = emptyList(),
    val longTestQuestions: List<String> = emptyList(),
    val answerKeySummary: String = "",
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class TestAttempt(
    @DocumentId val id: String = "",
    val userId: String = "",
    val subjectId: String = "",
    val subjectName: String = "",
    val chapterId: String = "",
    val chapterTitle: String = "",
    val totalQuestions: Int = 0,
    val correctAnswers: Int = 0,
    val percentage: Int = 0,
    val weakTopics: List<String> = emptyList(),
    val durationSeconds: Int = 0,
    @ServerTimestamp val createdAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class TutorMessage(
    @DocumentId val id: String = "",
    val userId: String = "",
    val subjectId: String = "",
    val chapterId: String = "",
    val role: String = TutorRole.USER.name,
    val content: String = "",
    val urduContent: String = "",
    val sourceNote: String = "",
    @ServerTimestamp val createdAt: Timestamp? = null
) {
    val roleEnum: TutorRole get() = TutorRole.fromRaw(role)
}
