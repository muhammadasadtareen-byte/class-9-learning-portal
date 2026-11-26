package com.example.data.seed

import android.content.Context
import com.example.data.model.MaterialCategory
import com.example.data.model.McqQuestion
import com.example.data.model.ProcessingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

data class SeedSubjectBundle(
    val subjectId: String,
    val curriculumSession: String,
    val curriculumWarning: String,
    val teachingStrategy: String,
    val materials: List<SeedMaterialSpec>,
    val chapters: List<SeedChapterSpec>,
    val mcqBanks: List<SeedMcqBankSpec>
)

object SubjectSeedLoader {
    const val CHEM_SUBJECT_ID = "sub_chemistry"
    const val PHY_SUBJECT_ID = "sub_physics"
    const val SEED_VERSION_TAG = "[TutorArchive v3]"

    @Volatile
    private var cachedBundles: List<SeedSubjectBundle>? = null

    suspend fun loadAll(context: Context?): List<SeedSubjectBundle> {
        cachedBundles?.let { return it }
        return withContext(Dispatchers.IO) {
            cachedBundles?.let { return@withContext it }
            val loaded = if (context == null) {
                listOf(fallbackChemistryBundle())
            } else {
                listOfNotNull(
                    runCatching { parseAsset(context, "seed_chemistry.json") }.getOrNull()
                        ?: fallbackChemistryBundle(),
                    runCatching { parseAsset(context, "seed_physics.json") }.getOrNull()
                )
            }
            cachedBundles = loaded
            loaded
        }
    }

    private fun parseAsset(context: Context, assetName: String): SeedSubjectBundle {
        val raw = context.assets.open(assetName).bufferedReader(Charsets.UTF_8).use { it.readText() }
        val root = JSONObject(raw)

        val materials = root.optJSONArray("materials").toObjectList { obj ->
            SeedMaterialSpec(
                id = obj.optString("id"),
                fileName = obj.optString("fileName"),
                mimeType = obj.optString("mimeType", "application/pdf"),
                category = MaterialCategory.fromRaw(obj.optString("category")),
                fileSizeBytes = obj.optLong("fileSizeBytes", 0L),
                localUri = obj.optString("localUri"),
                processingStatus = ProcessingStatus.fromRaw(obj.optString("processingStatus")),
                statusMessage = obj.optString("statusMessage"),
                sessionYearDetected = obj.optString("sessionYearDetected"),
                verifiedOfficialSource = obj.optBoolean("verifiedOfficialSource", true),
                extractedSummary = obj.optString("extractedSummary"),
                extractedText = obj.optString("extractedText"),
                pageCount = obj.optInt("pageCount", 0),
                unreadablePagesNote = obj.optString("unreadablePagesNote")
            )
        }

        val chapters = root.optJSONArray("chapters").toObjectList { obj ->
            SeedChapterSpec(
                id = obj.optString("id"),
                chapterNumber = obj.optInt("chapterNumber", 1),
                title = obj.optString("title"),
                urduTitle = obj.optString("urduTitle"),
                pageReference = obj.optString("pageReference"),
                introduction = obj.optString("introduction"),
                urduIntroduction = obj.optString("urduIntroduction"),
                learningObjectives = obj.optJSONArray("learningObjectives").toStringList(),
                officialSlos = obj.optJSONArray("officialSlos").toStringList(),
                topics = obj.optJSONArray("topics").toStringList(),
                completeNotes = obj.optString("completeNotes"),
                shortNotes = obj.optString("shortNotes"),
                definitions = obj.optString("definitions"),
                formulasAndConcepts = obj.optString("formulasAndConcepts"),
                solvedExamples = obj.optString("solvedExamples"),
                solvedExercises = obj.optString("solvedExercises"),
                importantQuestions = obj.optString("importantQuestions"),
                chapterSummary = obj.optString("chapterSummary"),
                sourceBasis = obj.optString("sourceBasis")
            )
        }

        val mcqBanks = root.optJSONArray("mcqBanks").toObjectList { obj ->
            val questions = obj.optJSONArray("questions").toObjectList { qObj ->
                val opts = qObj.optJSONArray("options").toStringList()
                McqQuestion(
                    number = qObj.optInt("number", 1),
                    question = qObj.optString("question"),
                    urduHint = qObj.optString("urduHint"),
                    options = (opts + listOf("", "", "", "")).take(4),
                    correctIndex = qObj.optInt("correctIndex", 0).coerceIn(0, 3),
                    explanation = qObj.optString("explanation"),
                    topicTag = qObj.optString("topicTag"),
                    sourceType = qObj.optString("sourceType", "AI_PRACTICE"),
                    pageRef = qObj.optString("pageRef")
                )
            }
            SeedMcqBankSpec(
                bankId = obj.optString("bankId"),
                chapterId = obj.optString("chapterId"),
                chapterTitle = obj.optString("chapterTitle"),
                questions = questions,
                shortTestQuestions = obj.optJSONArray("shortTestQuestions").toStringList(),
                longTestQuestions = obj.optJSONArray("longTestQuestions").toStringList(),
                answerKeySummary = obj.optString("answerKeySummary")
            )
        }

        return SeedSubjectBundle(
            subjectId = root.optString("subjectId"),
            curriculumSession = root.optString("curriculumSession"),
            curriculumWarning = root.optString("curriculumWarning"),
            teachingStrategy = root.optString("teachingStrategy"),
            materials = materials,
            chapters = chapters,
            mcqBanks = mcqBanks
        )
    }

    private fun fallbackChemistryBundle(): SeedSubjectBundle = SeedSubjectBundle(
        subjectId = CHEM_SUBJECT_ID,
        curriculumSession = ChemistrySeedModels.CHEM_CURRICULUM_SESSION,
        curriculumWarning = ChemistrySeedModels.CHEM_CURRICULUM_WARNING,
        teachingStrategy = ChemistrySeedModels.CHEM_TEACHING_STRATEGY,
        materials = ChemistrySeedModels.materials,
        chapters = ChemistrySeedModels.chapters,
        mcqBanks = ChemistrySeedModels.mcqBanks
    )

    private inline fun <T> JSONArray?.toObjectList(transform: (JSONObject) -> T): List<T> {
        if (this == null) return emptyList()
        val result = ArrayList<T>(length())
        for (i in 0 until length()) {
            val obj = optJSONObject(i) ?: continue
            result.add(transform(obj))
        }
        return result
    }

    private fun JSONArray?.toStringList(): List<String> {
        if (this == null) return emptyList()
        val result = ArrayList<String>(length())
        for (i in 0 until length()) {
            val item = optString(i).trim()
            if (item.isNotEmpty()) result.add(item)
        }
        return result
    }
}
