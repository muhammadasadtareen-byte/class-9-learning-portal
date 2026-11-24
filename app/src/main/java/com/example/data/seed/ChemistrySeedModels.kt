package com.example.data.seed

import com.example.data.model.MaterialCategory
import com.example.data.model.McqQuestion
import com.example.data.model.ProcessingStatus

data class SeedMaterialSpec(
    val id: String,
    val fileName: String,
    val mimeType: String,
    val category: MaterialCategory,
    val fileSizeBytes: Long,
    val localUri: String,
    val processingStatus: ProcessingStatus,
    val statusMessage: String,
    val sessionYearDetected: String,
    val verifiedOfficialSource: Boolean,
    val extractedSummary: String,
    val extractedText: String,
    val pageCount: Int,
    val unreadablePagesNote: String
)

data class SeedChapterSpec(
    val id: String,
    val chapterNumber: Int,
    val title: String,
    val urduTitle: String,
    val pageReference: String,
    val introduction: String,
    val urduIntroduction: String,
    val learningObjectives: List<String>,
    val officialSlos: List<String>,
    val topics: List<String>,
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

data class SeedMcqBankSpec(
    val bankId: String,
    val chapterId: String,
    val chapterTitle: String,
    val questions: List<McqQuestion>,
    val shortTestQuestions: List<String>,
    val longTestQuestions: List<String>,
    val answerKeySummary: String
)

object ChemistrySeedModels {
    const val CHEM_SUBJECT_ID = "sub_chemistry"

    // Must remain <= 60 chars per firestore.rules
    const val CHEM_CURRICULUM_SESSION = "NCP 2022-23 / Scheme 2024 (Exams to 2026)"

    // Must remain <= 500 chars per firestore.rules
    const val CHEM_CURRICULUM_WARNING =
        "Pre-loaded from 11 attached FBISE Chemistry files (NBF NCP 2022-23 textbook, Inclusive Scheme 2024 AF, Model Papers & 2023-2026 Past Papers). Official 2027-2028 FBISE syllabus is NOT uploaded yet; upload when issued to confirm alignment."

    // Must remain <= 2000 chars per firestore.rules
    const val CHEM_TEACHING_STRATEGY =
        "Teach FBISE Class 9 Chemistry (NBF Model Textbook, NCP 2022-23 / Inclusive Scheme 2024) across all 19 chapters. Emphasize exact SLOs (K/U/A cognitive levels), balanced chemical equations, SI units, and Urdu support for tricky chemistry terms. Solve all numerical problems using Given, Required, Formula, Substitution, Calculation, and Final Answer. Clearly distinguish Verified Official FBISE past/model paper questions from Practice Questions, and note that 2006-curriculum topics (e.g., Down's cell, Molarity, Charles's Law) appear only in archived older papers."

    val materials: List<SeedMaterialSpec>
        get() = ChemistryMaterialsSeed.allMaterials

    val chapters: List<SeedChapterSpec>
        get() = ChemistryChaptersSeed1To6.chapters +
            ChemistryChaptersSeed7To13.chapters +
            ChemistryChaptersSeed14To19.chapters

    val mcqBanks: List<SeedMcqBankSpec>
        get() = ChemistryMcqBanksSeedPart1.banks +
            ChemistryMcqBanksSeedPart2.banks
}
