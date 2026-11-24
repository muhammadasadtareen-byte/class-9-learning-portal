package com.example.data.repository

import android.content.Context
import com.example.R
import com.example.core.OperationType
import com.example.core.handleFirestoreError
import com.example.data.model.Chapter
import com.example.data.model.DefaultFbiseSubjects
import com.example.data.model.LanguageMode
import com.example.data.model.MaterialCategory
import com.example.data.model.McqBank
import com.example.data.model.McqQuestion
import com.example.data.model.ProcessingStatus
import com.example.data.model.StudentProfile
import com.example.data.model.StudyMaterial
import com.example.data.model.Subject
import com.example.data.model.TestAttempt
import com.example.data.model.TutorMessage
import com.example.data.model.TutorRole
import com.example.data.seed.ChemistrySeedModels
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import org.json.JSONArray
import org.json.JSONObject

class PortalRepository(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth = Firebase.auth
) {
    constructor(context: Context) : this(
        FirebaseFirestore.getInstance(
            context.applicationContext.getString(R.string.firestore_database_id)
        )
    )

    private fun requireUserId(expectedUserId: String? = null): String {
        val uid = auth.currentUser?.uid
            ?: throw IllegalStateException("User must be signed in with Google before accessing Firestore.")
        if (expectedUserId != null) {
            check(uid == expectedUserId) { "Signed-in user changed; refusing cross-user access." }
        }
        return uid
    }

    private fun userDoc(uid: String) = db.collection("users").document(uid)
    private fun subjectsCol(uid: String) = userDoc(uid).collection("subjects")
    private fun materialsCol(uid: String) = userDoc(uid).collection("materials")
    private fun chaptersCol(uid: String) = userDoc(uid).collection("chapters")
    private fun mcqBanksCol(uid: String) = userDoc(uid).collection("mcqBanks")
    private fun testAttemptsCol(uid: String) = userDoc(uid).collection("testAttempts")
    private fun tutorMessagesCol(uid: String) = userDoc(uid).collection("tutorMessages")

    private fun <T> Flow<T>.handleObserveErrors(uid: String, op: OperationType, path: String): Flow<T> =
        catch { error ->
            val firestoreError = generateSequence(error) { it.cause }
                .filterIsInstance<FirebaseFirestoreException>()
                .firstOrNull()
            if (firestoreError == null && error is CancellationException) throw error
            val exceptionToLog = firestoreError ?: (error as? Exception)
            if (exceptionToLog != null && auth.currentUser?.uid == uid) {
                handleFirestoreError(exceptionToLog, op, path)
            }
            throw error
        }

    fun observeProfile(userId: String): Flow<StudentProfile?> = flow {
        val uid = requireUserId(userId)
        val path = "users/$uid"
        emitAll(
            userDoc(uid).snapshots()
                .map { snap ->
                    if (snap.exists()) {
                        snap.toObject(StudentProfile::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                    } else null
                }
                .handleObserveErrors(uid, OperationType.GET, path)
        )
    }

    fun observeSubjects(userId: String): Flow<List<Subject>> = flow {
        val uid = requireUserId(userId)
        val path = "users/$uid/subjects"
        emitAll(
            subjectsCol(uid).snapshots()
                .map { snap ->
                    snap.toObjects(Subject::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedWith(compareBy<Subject> { it.sortOrder }.thenBy { it.name })
                }
                .handleObserveErrors(uid, OperationType.LIST, path)
        )
    }

    fun observeAllMaterials(userId: String): Flow<List<StudyMaterial>> = flow {
        val uid = requireUserId(userId)
        val path = "users/$uid/materials"
        emitAll(
            materialsCol(uid).snapshots()
                .map { snap ->
                    snap.toObjects(StudyMaterial::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedByDescending { it.createdAt ?: Timestamp(0, 0) }
                }
                .handleObserveErrors(uid, OperationType.LIST, path)
        )
    }

    fun observeAllChapters(userId: String): Flow<List<Chapter>> = flow {
        val uid = requireUserId(userId)
        val path = "users/$uid/chapters"
        emitAll(
            chaptersCol(uid).snapshots()
                .map { snap ->
                    snap.toObjects(Chapter::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedWith(compareBy<Chapter> { it.chapterNumber }.thenBy { it.title })
                }
                .handleObserveErrors(uid, OperationType.LIST, path)
        )
    }

    fun observeAllMcqBanks(userId: String): Flow<List<McqBank>> = flow {
        val uid = requireUserId(userId)
        val path = "users/$uid/mcqBanks"
        emitAll(
            mcqBanksCol(uid).snapshots()
                .map { snap ->
                    snap.toObjects(McqBank::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                }
                .handleObserveErrors(uid, OperationType.LIST, path)
        )
    }

    fun observeTestAttempts(userId: String): Flow<List<TestAttempt>> = flow {
        val uid = requireUserId(userId)
        val path = "users/$uid/testAttempts"
        emitAll(
            testAttemptsCol(uid).snapshots()
                .map { snap ->
                    snap.toObjects(TestAttempt::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedByDescending { it.createdAt ?: Timestamp(0, 0) }
                }
                .handleObserveErrors(uid, OperationType.LIST, path)
        )
    }

    fun observeTutorMessages(userId: String, subjectId: String): Flow<List<TutorMessage>> = flow {
        val uid = requireUserId(userId)
        val path = "users/$uid/tutorMessages"
        emitAll(
            tutorMessagesCol(uid)
                .whereEqualTo("subjectId", subjectId)
                .snapshots()
                .map { snap ->
                    snap.toObjects(TutorMessage::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
                        .sortedBy { it.createdAt ?: Timestamp(0, 0) }
                }
                .handleObserveErrors(uid, OperationType.LIST, path)
        )
    }

    suspend fun ensureProfileAndSeedDefaults(userId: String, fallbackName: String): Result<Unit> {
        val path = "users/$userId"
        return try {
            val uid = requireUserId(userId)
            val snap = userDoc(uid).get().await()
            val profile = if (snap.exists()) {
                snap.toObject(StudentProfile::class.java, DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)
            } else {
                val safeName = fallbackName.trim().ifBlank { "Class 9 Student" }.take(80)
                val payload = mapOf(
                    "userId" to uid,
                    "studentName" to safeName,
                    "className" to "Class 9",
                    "board" to "FBISE Pakistan",
                    "session" to "2027-2028",
                    "parentModeEnabled" to true,
                    "urduSupportEnabled" to true,
                    "lastStudiedSubjectId" to "",
                    "lastStudiedChapterId" to "",
                    "lastStudiedTopicTitle" to "",
                    "seededDefaults" to false,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                userDoc(uid).set(payload).await()
                StudentProfile(userId = uid, studentName = safeName, seededDefaults = false)
            }

            if (profile != null && !profile.seededDefaults) {
                val existingSubjects = subjectsCol(uid).get().await()
                if (existingSubjects.isEmpty) {
                    val batch = db.batch()
                    DefaultFbiseSubjects.suggestedSubjects.forEachIndexed { idx, tpl ->
                        val docId = "sub_${tpl.idSuffix}"
                        val subjectPayload = mapOf(
                            "userId" to uid,
                            "name" to tpl.name.take(80),
                            "code" to tpl.code.take(24),
                            "description" to tpl.description.take(400),
                            "iconKey" to tpl.iconKey.take(40),
                            "customIconUri" to "",
                            "accentColorKey" to tpl.accentColorKey.take(30),
                            "enabled" to true,
                            "languageMode" to tpl.languageMode.name,
                            "curriculumSession" to "Pending 2027-2028 Upload",
                            "curriculumVerified" to false,
                            "curriculumWarning" to DefaultFbiseSubjects.defaultWarningFor(tpl.name).take(500),
                            "teachingStrategy" to tpl.teachingStrategy.take(2000),
                            "sortOrder" to idx,
                            "chapterCount" to 0,
                            "completedChapterCount" to 0,
                            "materialCount" to 0,
                            "createdAt" to FieldValue.serverTimestamp(),
                            "updatedAt" to FieldValue.serverTimestamp()
                        )
                        batch.set(subjectsCol(uid).document(docId), subjectPayload)
                    }
                    batch.update(
                        userDoc(uid),
                        mapOf(
                            "seededDefaults" to true,
                            "updatedAt" to FieldValue.serverTimestamp()
                        )
                    )
                    batch.commit().await()
                } else {
                    userDoc(uid).update(
                        mapOf(
                            "seededDefaults" to true,
                            "updatedAt" to FieldValue.serverTimestamp()
                        )
                    ).await()
                }
            }

            seedChemistryIfNeeded(uid)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, path)
            Result.failure(e)
        }
    }

    private suspend fun seedChemistryIfNeeded(uid: String) {
        val chemSubjectRef = subjectsCol(uid).document(ChemistrySeedModels.CHEM_SUBJECT_ID)
        val chemSubjectSnap = chemSubjectRef.get().await()
        if (!chemSubjectSnap.exists()) return

        val hasChapters = !chaptersCol(uid)
            .whereEqualTo("subjectId", ChemistrySeedModels.CHEM_SUBJECT_ID)
            .limit(1)
            .get()
            .await()
            .isEmpty
        val hasMaterials = !materialsCol(uid)
            .whereEqualTo("subjectId", ChemistrySeedModels.CHEM_SUBJECT_ID)
            .limit(1)
            .get()
            .await()
            .isEmpty
        if (hasChapters || hasMaterials) return

        val batch = db.batch()
        val now = FieldValue.serverTimestamp()
        val subjectId = ChemistrySeedModels.CHEM_SUBJECT_ID

        ChemistrySeedModels.materials.forEach { mat ->
            val matPayload = mapOf(
                "userId" to uid,
                "subjectId" to subjectId,
                "fileName" to mat.fileName.trim().ifBlank { "Untitled_Document" }.take(200),
                "mimeType" to mat.mimeType.trim().ifBlank { "application/pdf" }.take(120),
                "category" to mat.category.name,
                "fileSizeBytes" to mat.fileSizeBytes.coerceIn(0L, 524288000L),
                "localUri" to mat.localUri.take(600),
                "processingStatus" to mat.processingStatus.name,
                "statusMessage" to mat.statusMessage.take(500),
                "sessionYearDetected" to mat.sessionYearDetected.take(60),
                "verifiedOfficialSource" to mat.verifiedOfficialSource,
                "extractedSummary" to mat.extractedSummary.take(20000),
                "extractedText" to mat.extractedText.take(120000),
                "pageCount" to mat.pageCount.coerceIn(0, 2000),
                "unreadablePagesNote" to mat.unreadablePagesNote.take(500),
                "createdAt" to now,
                "updatedAt" to now
            )
            batch.set(materialsCol(uid).document(mat.id), matPayload)
        }

        ChemistrySeedModels.chapters.forEach { ch ->
            val chapterPayload = mapOf(
                "userId" to uid,
                "subjectId" to subjectId,
                "chapterNumber" to ch.chapterNumber.coerceIn(1, 100),
                "title" to ch.title.trim().take(160),
                "urduTitle" to ch.urduTitle.trim().take(160),
                "pageReference" to ch.pageReference.trim().take(80),
                "introduction" to ch.introduction.trim().take(3000),
                "urduIntroduction" to ch.urduIntroduction.trim().take(3000),
                "learningObjectives" to ch.learningObjectives.map { it.trim() }.filter { it.isNotEmpty() }.take(30),
                "officialSlos" to ch.officialSlos.map { it.trim() }.filter { it.isNotEmpty() }.take(40),
                "topics" to ch.topics.map { it.trim() }.filter { it.isNotEmpty() }.take(40),
                "completeNotes" to ch.completeNotes.take(40000),
                "shortNotes" to ch.shortNotes.take(20000),
                "definitions" to ch.definitions.take(20000),
                "formulasAndConcepts" to ch.formulasAndConcepts.take(20000),
                "solvedExamples" to ch.solvedExamples.take(20000),
                "solvedExercises" to ch.solvedExercises.take(40000),
                "importantQuestions" to ch.importantQuestions.take(25000),
                "chapterSummary" to ch.chapterSummary.take(10000),
                "sourceBasis" to ch.sourceBasis.trim().take(300),
                "completed" to false,
                "masteryPercent" to 0,
                "lastQuizScorePercent" to 0,
                "createdAt" to now,
                "updatedAt" to now
            )
            batch.set(chaptersCol(uid).document(ch.id), chapterPayload)
        }

        ChemistrySeedModels.mcqBanks.forEach { bank ->
            val questionMaps = bank.questions.take(60).map { question ->
                val padded = if (question.options.size == 4) {
                    question
                } else {
                    question.copy(options = (question.options + listOf("", "", "", "")).take(4))
                }
                padded.toMap()
            }
            val bankPayload = mapOf(
                "userId" to uid,
                "subjectId" to subjectId,
                "chapterId" to bank.chapterId,
                "chapterTitle" to bank.chapterTitle.trim().take(160),
                "questions" to questionMaps,
                "shortTestQuestions" to bank.shortTestQuestions.take(25),
                "longTestQuestions" to bank.longTestQuestions.take(15),
                "answerKeySummary" to bank.answerKeySummary.take(30000),
                "createdAt" to now,
                "updatedAt" to now
            )
            batch.set(mcqBanksCol(uid).document(bank.bankId), bankPayload)
        }

        batch.update(
            chemSubjectRef,
            mapOf(
                "curriculumSession" to ChemistrySeedModels.CHEM_CURRICULUM_SESSION.take(60),
                "curriculumVerified" to false,
                "curriculumWarning" to ChemistrySeedModels.CHEM_CURRICULUM_WARNING.take(500),
                "teachingStrategy" to ChemistrySeedModels.CHEM_TEACHING_STRATEGY.take(2000),
                "chapterCount" to ChemistrySeedModels.chapters.size.coerceIn(0, 200),
                "completedChapterCount" to 0,
                "materialCount" to ChemistrySeedModels.materials.size.coerceIn(0, 500),
                "updatedAt" to now
            )
        )

        batch.commit().await()
    }

    suspend fun updateProfileSettings(
        userId: String,
        studentName: String? = null,
        parentModeEnabled: Boolean? = null,
        urduSupportEnabled: Boolean? = null
    ): Result<Unit> {
        val path = "users/$userId"
        return try {
            val uid = requireUserId(userId)
            val updates = mutableMapOf<String, Any>("updatedAt" to FieldValue.serverTimestamp())
            studentName?.trim()?.takeIf { it.isNotEmpty() }?.let { updates["studentName"] = it.take(80) }
            parentModeEnabled?.let { updates["parentModeEnabled"] = it }
            urduSupportEnabled?.let { updates["urduSupportEnabled"] = it }
            userDoc(uid).update(updates).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun updateResumePoint(
        userId: String,
        subjectId: String,
        chapterId: String,
        topicTitle: String
    ): Result<Unit> {
        val path = "users/$userId"
        return try {
            val uid = requireUserId(userId)
            userDoc(uid).update(
                mapOf(
                    "lastStudiedSubjectId" to subjectId.take(128),
                    "lastStudiedChapterId" to chapterId.take(128),
                    "lastStudiedTopicTitle" to topicTitle.take(200),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    // --- SUBJECT CRUD ---

    suspend fun createSubject(
        userId: String,
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String,
        sortOrder: Int
    ): Result<String> {
        var path = "users/$userId/subjects"
        return try {
            val uid = requireUserId(userId)
            val cleanName = name.trim().take(80)
            require(cleanName.isNotEmpty()) { "Subject name cannot be empty." }
            val docRef = subjectsCol(uid).document()
            path = docRef.path
            val strategy = teachingStrategy.trim().ifBlank {
                DefaultFbiseSubjects.defaultStrategyForCustom(cleanName)
            }.take(2000)
            val payload = mapOf(
                "userId" to uid,
                "name" to cleanName,
                "code" to code.trim().take(24),
                "description" to description.trim().take(400),
                "iconKey" to iconKey.trim().ifBlank { "school" }.take(40),
                "customIconUri" to customIconUri.trim().take(600),
                "accentColorKey" to accentColorKey.trim().ifBlank { "emerald" }.take(30),
                "enabled" to true,
                "languageMode" to languageMode.name,
                "curriculumSession" to "Pending 2027-2028 Upload",
                "curriculumVerified" to false,
                "curriculumWarning" to DefaultFbiseSubjects.defaultWarningFor(cleanName).take(500),
                "teachingStrategy" to strategy,
                "sortOrder" to sortOrder.coerceIn(0, 500),
                "chapterCount" to 0,
                "completedChapterCount" to 0,
                "materialCount" to 0,
                "createdAt" to FieldValue.serverTimestamp(),
                "updatedAt" to FieldValue.serverTimestamp()
            )
            docRef.set(payload).await()
            Result.success(docRef.id)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, path)
            Result.failure(e)
        }
    }

    suspend fun updateSubject(
        userId: String,
        subjectId: String,
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String,
        curriculumSession: String? = null,
        curriculumVerified: Boolean? = null,
        curriculumWarning: String? = null
    ): Result<Unit> {
        val path = "users/$userId/subjects/$subjectId"
        return try {
            val uid = requireUserId(userId)
            val cleanName = name.trim().take(80)
            require(cleanName.isNotEmpty()) { "Subject name cannot be empty." }
            val updates = mutableMapOf<String, Any>(
                "name" to cleanName,
                "code" to code.trim().take(24),
                "description" to description.trim().take(400),
                "iconKey" to iconKey.trim().ifBlank { "school" }.take(40),
                "customIconUri" to customIconUri.trim().take(600),
                "accentColorKey" to accentColorKey.trim().ifBlank { "emerald" }.take(30),
                "languageMode" to languageMode.name,
                "teachingStrategy" to teachingStrategy.trim().take(2000),
                "updatedAt" to FieldValue.serverTimestamp()
            )
            curriculumSession?.let { updates["curriculumSession"] = it.trim().take(60) }
            curriculumVerified?.let { updates["curriculumVerified"] = it }
            curriculumWarning?.let { updates["curriculumWarning"] = it.trim().take(500) }
            subjectsCol(uid).document(subjectId).update(updates).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun toggleSubjectEnabled(userId: String, subjectId: String, enabled: Boolean): Result<Unit> {
        val path = "users/$userId/subjects/$subjectId"
        return try {
            val uid = requireUserId(userId)
            subjectsCol(uid).document(subjectId).update(
                mapOf(
                    "enabled" to enabled,
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun updateSubjectCurriculumStatus(
        userId: String,
        subjectId: String,
        sessionDetected: String,
        verified: Boolean,
        warning: String
    ): Result<Unit> {
        val path = "users/$userId/subjects/$subjectId"
        return try {
            val uid = requireUserId(userId)
            subjectsCol(uid).document(subjectId).update(
                mapOf(
                    "curriculumSession" to sessionDetected.trim().take(60),
                    "curriculumVerified" to verified,
                    "curriculumWarning" to warning.trim().take(500),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun syncSubjectCounters(userId: String, subjectId: String): Result<Unit> {
        val path = "users/$userId/subjects/$subjectId"
        return try {
            val uid = requireUserId(userId)
            val chaptersSnap = chaptersCol(uid).whereEqualTo("subjectId", subjectId).get().await()
            val materialsSnap = materialsCol(uid).whereEqualTo("subjectId", subjectId).get().await()
            val totalChapters = chaptersSnap.size().coerceIn(0, 200)
            val completedChapters = chaptersSnap.documents.count { it.getBoolean("completed") == true }.coerceIn(0, 200)
            val totalMaterials = materialsSnap.size().coerceIn(0, 500)
            subjectsCol(uid).document(subjectId).update(
                mapOf(
                    "chapterCount" to totalChapters,
                    "completedChapterCount" to completedChapters,
                    "materialCount" to totalMaterials,
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun deleteSubjectCascade(userId: String, subjectId: String): Result<Unit> {
        val path = "users/$userId/subjects/$subjectId"
        return try {
            val uid = requireUserId(userId)
            val materials = materialsCol(uid).whereEqualTo("subjectId", subjectId).get().await()
            val chapters = chaptersCol(uid).whereEqualTo("subjectId", subjectId).get().await()
            val banks = mcqBanksCol(uid).whereEqualTo("subjectId", subjectId).get().await()
            val messages = tutorMessagesCol(uid).whereEqualTo("subjectId", subjectId).get().await()
            val attempts = testAttemptsCol(uid).whereEqualTo("subjectId", subjectId).get().await()

            val refsToDelete = buildList {
                add(subjectsCol(uid).document(subjectId))
                materials.documents.forEach { add(it.reference) }
                chapters.documents.forEach { add(it.reference) }
                banks.documents.forEach { add(it.reference) }
                messages.documents.forEach { add(it.reference) }
                attempts.documents.forEach { add(it.reference) }
            }
            refsToDelete.chunked(400).forEach { chunk ->
                val batch = db.batch()
                chunk.forEach { batch.delete(it) }
                batch.commit().await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, path)
            Result.failure(e)
        }
    }

    // --- MATERIALS CRUD ---

    suspend fun createMaterial(
        userId: String,
        subjectId: String,
        fileName: String,
        mimeType: String,
        category: MaterialCategory,
        fileSizeBytes: Long,
        localUri: String,
        pageCount: Int,
        initialStatus: ProcessingStatus = ProcessingStatus.PROCESSING,
        statusMessage: String = "Analyzing document with Gemini AI…"
    ): Result<String> {
        var path = "users/$userId/materials"
        return try {
            val uid = requireUserId(userId)
            val docRef = materialsCol(uid).document()
            path = docRef.path
            val payload = mapOf(
                "userId" to uid,
                "subjectId" to subjectId,
                "fileName" to fileName.trim().ifBlank { "Untitled_Document" }.take(200),
                "mimeType" to mimeType.trim().ifBlank { "application/octet-stream" }.take(120),
                "category" to category.name,
                "fileSizeBytes" to fileSizeBytes.coerceIn(0L, 524288000L),
                "localUri" to localUri.take(600),
                "processingStatus" to initialStatus.name,
                "statusMessage" to statusMessage.take(500),
                "sessionYearDetected" to "Pending Analysis",
                "verifiedOfficialSource" to false,
                "extractedSummary" to "",
                "extractedText" to "",
                "pageCount" to pageCount.coerceIn(0, 2000),
                "unreadablePagesNote" to "",
                "createdAt" to FieldValue.serverTimestamp(),
                "updatedAt" to FieldValue.serverTimestamp()
            )
            docRef.set(payload).await()
            syncSubjectCounters(uid, subjectId)
            Result.success(docRef.id)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, path)
            Result.failure(e)
        }
    }

    suspend fun updateMaterialAnalysis(
        userId: String,
        materialId: String,
        status: ProcessingStatus,
        statusMessage: String,
        sessionYearDetected: String,
        verifiedOfficialSource: Boolean,
        extractedSummary: String,
        extractedText: String,
        pageCount: Int,
        unreadablePagesNote: String
    ): Result<Unit> {
        val path = "users/$userId/materials/$materialId"
        return try {
            val uid = requireUserId(userId)
            materialsCol(uid).document(materialId).update(
                mapOf(
                    "processingStatus" to status.name,
                    "statusMessage" to statusMessage.take(500),
                    "sessionYearDetected" to sessionYearDetected.take(60),
                    "verifiedOfficialSource" to verifiedOfficialSource,
                    "extractedSummary" to extractedSummary.take(20000),
                    "extractedText" to extractedText.take(120000),
                    "pageCount" to pageCount.coerceIn(0, 2000),
                    "unreadablePagesNote" to unreadablePagesNote.take(500),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun deleteMaterial(userId: String, subjectId: String, materialId: String): Result<Unit> {
        val path = "users/$userId/materials/$materialId"
        return try {
            val uid = requireUserId(userId)
            materialsCol(uid).document(materialId).delete().await()
            syncSubjectCounters(uid, subjectId)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, path)
            Result.failure(e)
        }
    }

    // --- CHAPTERS CRUD ---

    suspend fun createChapter(
        userId: String,
        subjectId: String,
        chapterNumber: Int,
        title: String,
        urduTitle: String = "",
        pageReference: String = "",
        introduction: String = "",
        urduIntroduction: String = "",
        learningObjectives: List<String> = emptyList(),
        officialSlos: List<String> = emptyList(),
        topics: List<String> = emptyList(),
        sourceBasis: String = ""
    ): Result<String> {
        var path = "users/$userId/chapters"
        return try {
            val uid = requireUserId(userId)
            val cleanTitle = title.trim().take(160)
            require(cleanTitle.isNotEmpty()) { "Chapter title cannot be empty." }
            val docRef = chaptersCol(uid).document()
            path = docRef.path
            val payload = mapOf(
                "userId" to uid,
                "subjectId" to subjectId,
                "chapterNumber" to chapterNumber.coerceIn(1, 100),
                "title" to cleanTitle,
                "urduTitle" to urduTitle.trim().take(160),
                "pageReference" to pageReference.trim().take(80),
                "introduction" to introduction.trim().take(3000),
                "urduIntroduction" to urduIntroduction.trim().take(3000),
                "learningObjectives" to learningObjectives.map { it.trim() }.filter { it.isNotEmpty() }.take(30),
                "officialSlos" to officialSlos.map { it.trim() }.filter { it.isNotEmpty() }.take(40),
                "topics" to topics.map { it.trim() }.filter { it.isNotEmpty() }.take(40),
                "completeNotes" to "",
                "shortNotes" to "",
                "definitions" to "",
                "formulasAndConcepts" to "",
                "solvedExamples" to "",
                "solvedExercises" to "",
                "importantQuestions" to "",
                "chapterSummary" to "",
                "sourceBasis" to sourceBasis.trim().take(300),
                "completed" to false,
                "masteryPercent" to 0,
                "lastQuizScorePercent" to 0,
                "createdAt" to FieldValue.serverTimestamp(),
                "updatedAt" to FieldValue.serverTimestamp()
            )
            docRef.set(payload).await()
            syncSubjectCounters(uid, subjectId)
            Result.success(docRef.id)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, path)
            Result.failure(e)
        }
    }

    suspend fun updateChapterMetadata(
        userId: String,
        chapterId: String,
        chapterNumber: Int,
        title: String,
        urduTitle: String,
        pageReference: String,
        topics: List<String>,
        officialSlos: List<String>
    ): Result<Unit> {
        val path = "users/$userId/chapters/$chapterId"
        return try {
            val uid = requireUserId(userId)
            val cleanTitle = title.trim().take(160)
            require(cleanTitle.isNotEmpty()) { "Chapter title cannot be empty." }
            chaptersCol(uid).document(chapterId).update(
                mapOf(
                    "chapterNumber" to chapterNumber.coerceIn(1, 100),
                    "title" to cleanTitle,
                    "urduTitle" to urduTitle.trim().take(160),
                    "pageReference" to pageReference.trim().take(80),
                    "topics" to topics.map { it.trim() }.filter { it.isNotEmpty() }.take(40),
                    "officialSlos" to officialSlos.map { it.trim() }.filter { it.isNotEmpty() }.take(40),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun updateChapterStudyContent(
        userId: String,
        chapterId: String,
        introduction: String? = null,
        urduIntroduction: String? = null,
        learningObjectives: List<String>? = null,
        completeNotes: String? = null,
        shortNotes: String? = null,
        definitions: String? = null,
        formulasAndConcepts: String? = null,
        solvedExamples: String? = null,
        solvedExercises: String? = null,
        importantQuestions: String? = null,
        chapterSummary: String? = null,
        sourceBasis: String? = null
    ): Result<Unit> {
        val path = "users/$userId/chapters/$chapterId"
        return try {
            val uid = requireUserId(userId)
            val updates = mutableMapOf<String, Any>("updatedAt" to FieldValue.serverTimestamp())
            introduction?.let { updates["introduction"] = it.take(3000) }
            urduIntroduction?.let { updates["urduIntroduction"] = it.take(3000) }
            learningObjectives?.let { updates["learningObjectives"] = it.map { s -> s.trim() }.filter { s -> s.isNotEmpty() }.take(30) }
            completeNotes?.let { updates["completeNotes"] = it.take(40000) }
            shortNotes?.let { updates["shortNotes"] = it.take(20000) }
            definitions?.let { updates["definitions"] = it.take(20000) }
            formulasAndConcepts?.let { updates["formulasAndConcepts"] = it.take(20000) }
            solvedExamples?.let { updates["solvedExamples"] = it.take(20000) }
            solvedExercises?.let { updates["solvedExercises"] = it.take(40000) }
            importantQuestions?.let { updates["importantQuestions"] = it.take(25000) }
            chapterSummary?.let { updates["chapterSummary"] = it.take(10000) }
            sourceBasis?.let { updates["sourceBasis"] = it.take(300) }
            chaptersCol(uid).document(chapterId).update(updates).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun toggleChapterCompleted(
        userId: String,
        subjectId: String,
        chapterId: String,
        completed: Boolean,
        currentMastery: Int
    ): Result<Unit> {
        val path = "users/$userId/chapters/$chapterId"
        return try {
            val uid = requireUserId(userId)
            val nextMastery = if (completed) maxOf(currentMastery, 100) else minOf(currentMastery, 60)
            chaptersCol(uid).document(chapterId).update(
                mapOf(
                    "completed" to completed,
                    "masteryPercent" to nextMastery.coerceIn(0, 100),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            syncSubjectCounters(uid, subjectId)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun deleteChapter(userId: String, subjectId: String, chapterId: String): Result<Unit> {
        val path = "users/$userId/chapters/$chapterId"
        return try {
            val uid = requireUserId(userId)
            chaptersCol(uid).document(chapterId).delete().await()
            // Remove linked MCQ bank if present
            val bankRef = mcqBanksCol(uid).document("bank_$chapterId")
            val bankSnap = bankRef.get().await()
            if (bankSnap.exists()) {
                bankRef.delete().await()
            }
            syncSubjectCounters(uid, subjectId)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, path)
            Result.failure(e)
        }
    }

    // --- MCQ BANKS & TEST ATTEMPTS ---

    suspend fun saveMcqBank(
        userId: String,
        subjectId: String,
        chapterId: String,
        chapterTitle: String,
        questions: List<McqQuestion>,
        shortTestQuestions: List<String>,
        longTestQuestions: List<String>,
        answerKeySummary: String
    ): Result<Unit> {
        val bankId = "bank_${chapterId.take(100)}"
        val path = "users/$userId/mcqBanks/$bankId"
        return try {
            val uid = requireUserId(userId)
            val docRef = mcqBanksCol(uid).document(bankId)
            val existing = docRef.get().await()
            val qMaps = questions.take(60).map { it.toMap() }
            if (existing.exists()) {
                docRef.update(
                    mapOf(
                        "chapterTitle" to chapterTitle.trim().take(160),
                        "questions" to qMaps,
                        "shortTestQuestions" to shortTestQuestions.take(25),
                        "longTestQuestions" to longTestQuestions.take(15),
                        "answerKeySummary" to answerKeySummary.take(30000),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            } else {
                docRef.set(
                    mapOf(
                        "userId" to uid,
                        "subjectId" to subjectId,
                        "chapterId" to chapterId,
                        "chapterTitle" to chapterTitle.trim().take(160),
                        "questions" to qMaps,
                        "shortTestQuestions" to shortTestQuestions.take(25),
                        "longTestQuestions" to longTestQuestions.take(15),
                        "answerKeySummary" to answerKeySummary.take(30000),
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, path)
            Result.failure(e)
        }
    }

    suspend fun recordTestAttempt(
        userId: String,
        subjectId: String,
        subjectName: String,
        chapterId: String,
        chapterTitle: String,
        totalQuestions: Int,
        correctAnswers: Int,
        weakTopics: List<String>,
        durationSeconds: Int
    ): Result<Unit> {
        var path = "users/$userId/testAttempts"
        return try {
            val uid = requireUserId(userId)
            val safeTotal = totalQuestions.coerceIn(1, 100)
            val safeCorrect = correctAnswers.coerceIn(0, safeTotal)
            val pct = ((safeCorrect * 100) / safeTotal).coerceIn(0, 100)
            val docRef = testAttemptsCol(uid).document()
            path = docRef.path
            docRef.set(
                mapOf(
                    "userId" to uid,
                    "subjectId" to subjectId,
                    "subjectName" to subjectName.trim().take(80),
                    "chapterId" to chapterId,
                    "chapterTitle" to chapterTitle.trim().take(160),
                    "totalQuestions" to safeTotal,
                    "correctAnswers" to safeCorrect,
                    "percentage" to pct,
                    "weakTopics" to weakTopics.distinct().take(20),
                    "durationSeconds" to durationSeconds.coerceIn(0, 18000),
                    "createdAt" to FieldValue.serverTimestamp()
                )
            ).await()

            // Update chapter quiz score & mastery
            chaptersCol(uid).document(chapterId).update(
                mapOf(
                    "lastQuizScorePercent" to pct,
                    "masteryPercent" to pct,
                    "completed" to (pct >= 80),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            syncSubjectCounters(uid, subjectId)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, path)
            Result.failure(e)
        }
    }

    // --- SUBJECT-ISOLATED AI TUTOR MESSAGES ---

    suspend fun addTutorMessage(
        userId: String,
        subjectId: String,
        chapterId: String,
        role: TutorRole,
        content: String,
        urduContent: String = "",
        sourceNote: String = ""
    ): Result<Unit> {
        var path = "users/$userId/tutorMessages"
        return try {
            val uid = requireUserId(userId)
            val cleanContent = content.trim().take(20000)
            require(cleanContent.isNotEmpty()) { "Message cannot be empty." }
            val docRef = tutorMessagesCol(uid).document()
            path = docRef.path
            docRef.set(
                mapOf(
                    "userId" to uid,
                    "subjectId" to subjectId,
                    "chapterId" to chapterId.take(128),
                    "role" to role.name,
                    "content" to cleanContent,
                    "urduContent" to urduContent.trim().take(15000),
                    "sourceNote" to sourceNote.trim().take(400),
                    "createdAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, path)
            Result.failure(e)
        }
    }

    suspend fun clearTutorHistory(userId: String, subjectId: String): Result<Unit> {
        val path = "users/$userId/tutorMessages"
        return try {
            val uid = requireUserId(userId)
            val snap = tutorMessagesCol(uid).whereEqualTo("subjectId", subjectId).get().await()
            snap.documents.chunked(400).forEach { chunk ->
                val batch = db.batch()
                chunk.forEach { batch.delete(it.reference) }
                batch.commit().await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, path)
            Result.failure(e)
        }
    }

    // --- FULL PORTAL BACKUP EXPORT ---

    fun buildBackupJson(
        profile: StudentProfile?,
        subjects: List<Subject>,
        chapters: List<Chapter>,
        materials: List<StudyMaterial>,
        attempts: List<TestAttempt>
    ): String {
        val root = JSONObject()
        root.put("portal", "Class 9 Learning Portal (FBISE Pakistan 2027-2028)")
        root.put("exportedAtMillis", System.currentTimeMillis())
        root.put("studentName", profile?.studentName ?: "Class 9 Student")
        root.put("session", profile?.session ?: "2027-2028")

        val subjectsArr = JSONArray()
        subjects.forEach { s ->
            subjectsArr.put(
                JSONObject().apply {
                    put("id", s.id)
                    put("name", s.name)
                    put("code", s.code)
                    put("enabled", s.enabled)
                    put("curriculumSession", s.curriculumSession)
                    put("curriculumVerified", s.curriculumVerified)
                    put("chapterCount", s.chapterCount)
                    put("completedChapterCount", s.completedChapterCount)
                    put("materialCount", s.materialCount)
                }
            )
        }
        root.put("subjects", subjectsArr)

        val chaptersArr = JSONArray()
        chapters.forEach { c ->
            chaptersArr.put(
                JSONObject().apply {
                    put("id", c.id)
                    put("subjectId", c.subjectId)
                    put("chapterNumber", c.chapterNumber)
                    put("title", c.title)
                    put("urduTitle", c.urduTitle)
                    put("pageReference", c.pageReference)
                    put("completed", c.completed)
                    put("masteryPercent", c.masteryPercent)
                    put("topics", JSONArray(c.topics))
                    put("officialSlos", JSONArray(c.officialSlos))
                    put("completeNotes", c.completeNotes)
                    put("shortNotes", c.shortNotes)
                    put("solvedExercises", c.solvedExercises)
                }
            )
        }
        root.put("chapters", chaptersArr)

        val materialsArr = JSONArray()
        materials.forEach { m ->
            materialsArr.put(
                JSONObject().apply {
                    put("id", m.id)
                    put("subjectId", m.subjectId)
                    put("fileName", m.fileName)
                    put("category", m.category)
                    put("processingStatus", m.processingStatus)
                    put("sessionYearDetected", m.sessionYearDetected)
                    put("pageCount", m.pageCount)
                    put("extractedSummary", m.extractedSummary)
                }
            )
        }
        root.put("materials", materialsArr)

        val attemptsArr = JSONArray()
        attempts.forEach { a ->
            attemptsArr.put(
                JSONObject().apply {
                    put("subjectName", a.subjectName)
                    put("chapterTitle", a.chapterTitle)
                    put("correctAnswers", a.correctAnswers)
                    put("totalQuestions", a.totalQuestions)
                    put("percentage", a.percentage)
                    put("weakTopics", JSONArray(a.weakTopics))
                }
            )
        }
        root.put("testAttempts", attemptsArr)
        return root.toString(2)
    }
}
