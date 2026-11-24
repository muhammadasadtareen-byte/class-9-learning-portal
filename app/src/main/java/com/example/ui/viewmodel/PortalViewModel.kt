package com.example.ui.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ai.GeminiAcademicService
import com.example.data.document.DocumentIngestionEngine
import com.example.data.model.Chapter
import com.example.data.model.LanguageMode
import com.example.data.model.MaterialCategory
import com.example.data.model.McqBank
import com.example.data.model.ProcessingStatus
import com.example.data.model.StudentProfile
import com.example.data.model.StudyMaterial
import com.example.data.model.Subject
import com.example.data.model.TestAttempt
import com.example.data.model.TutorMessage
import com.example.data.model.TutorRole
import com.example.data.pdf.AcademicPdfExporter
import com.example.data.pdf.ExportedPdfResult
import com.example.data.pdf.PdfExportType
import com.example.data.repository.PortalRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface DataState<out T> {
    data object Loading : DataState<Nothing>
    data class Success<T>(val data: T) : DataState<T>
    data class Error(val message: String) : DataState<Nothing>
}

sealed interface AiTaskState {
    data object Idle : AiTaskState
    data class Busy(val label: String) : AiTaskState
    data class Streaming(val partialText: String) : AiTaskState
    data class Error(val message: String) : AiTaskState
}

@OptIn(ExperimentalCoroutinesApi::class)
class PortalViewModel(
    private val repository: PortalRepository,
    private val aiService: GeminiAcademicService,
    val currentUserId: String,
    defaultDisplayName: String
) : ViewModel() {

    companion object {
        private const val TAG = "PortalViewModel"
        private const val STOP_TIMEOUT_MS = 5000L
    }

    private val _aiTaskState = MutableStateFlow<AiTaskState>(AiTaskState.Idle)
    val aiTaskState: StateFlow<AiTaskState> = _aiTaskState.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    private val _activeTutorSubjectId = MutableStateFlow<String?>(null)

    private fun <T> kotlinx.coroutines.flow.Flow<T>.toDataState(
        fallbackMessage: String
    ): StateFlow<DataState<T>> =
        map<T, DataState<T>> { DataState.Success(it) }
            .catch { error ->
                val firestoreError = generateSequence(error) { it.cause }
                    .filterIsInstance<FirebaseFirestoreException>()
                    .firstOrNull()
                if (firestoreError == null && error is CancellationException) throw error
                val root = firestoreError ?: error
                Log.w(TAG, fallbackMessage, root)
                emit(DataState.Error(root.message ?: fallbackMessage))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
                initialValue = DataState.Loading
            )

    val profileState: StateFlow<DataState<StudentProfile?>> =
        repository.observeProfile(currentUserId).toDataState("Failed to load student profile")

    val subjectsState: StateFlow<DataState<List<Subject>>> =
        repository.observeSubjects(currentUserId).toDataState("Failed to load subjects")

    val materialsState: StateFlow<DataState<List<StudyMaterial>>> =
        repository.observeAllMaterials(currentUserId).toDataState("Failed to load materials")

    val chaptersState: StateFlow<DataState<List<Chapter>>> =
        repository.observeAllChapters(currentUserId).toDataState("Failed to load chapters")

    val mcqBanksState: StateFlow<DataState<List<McqBank>>> =
        repository.observeAllMcqBanks(currentUserId).toDataState("Failed to load MCQ banks")

    val testAttemptsState: StateFlow<DataState<List<TestAttempt>>> =
        repository.observeTestAttempts(currentUserId).toDataState("Failed to load test attempts")

    val tutorMessagesState: StateFlow<DataState<List<TutorMessage>>> =
        _activeTutorSubjectId.flatMapLatest { subjectId ->
            if (subjectId.isNullOrBlank()) {
                flowOf(emptyList())
            } else {
                repository.observeTutorMessages(currentUserId, subjectId)
            }
        }.toDataState("Failed to load tutor conversation")

    init {
        viewModelScope.launch {
            val result = repository.ensureProfileAndSeedDefaults(currentUserId, defaultDisplayName)
            result.onFailure { e ->
                _snackbarMessage.value = e.localizedMessage ?: "Failed to initialize study portal."
            }
        }
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }

    fun clearAiError() {
        if (_aiTaskState.value is AiTaskState.Error) {
            _aiTaskState.value = AiTaskState.Idle
        }
    }

    fun setActiveTutorSubject(subjectId: String?) {
        _activeTutorSubjectId.value = subjectId
    }

    // --- PROFILE & RESUME ---

    fun updateProfileSettings(
        studentName: String? = null,
        parentModeEnabled: Boolean? = null,
        urduSupportEnabled: Boolean? = null
    ) {
        viewModelScope.launch {
            repository.updateProfileSettings(
                userId = currentUserId,
                studentName = studentName,
                parentModeEnabled = parentModeEnabled,
                urduSupportEnabled = urduSupportEnabled
            ).onSuccess {
                _snackbarMessage.value = "Portal settings updated."
            }.onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Could not update settings."
            }
        }
    }

    fun recordResumePoint(subjectId: String, chapterId: String, topicTitle: String) {
        viewModelScope.launch {
            repository.updateResumePoint(currentUserId, subjectId, chapterId, topicTitle)
        }
    }

    // --- SUBJECT MANAGEMENT ---

    fun addSubject(
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String,
        currentSubjectCount: Int
    ) {
        viewModelScope.launch {
            repository.createSubject(
                userId = currentUserId,
                name = name,
                code = code,
                description = description,
                iconKey = iconKey,
                customIconUri = customIconUri,
                accentColorKey = accentColorKey,
                languageMode = languageMode,
                teachingStrategy = teachingStrategy,
                sortOrder = currentSubjectCount
            ).onSuccess {
                _snackbarMessage.value = "Added subject \"$name\"."
            }.onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Failed to add subject."
            }
        }
    }

    fun editSubject(
        subjectId: String,
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String
    ) {
        viewModelScope.launch {
            repository.updateSubject(
                userId = currentUserId,
                subjectId = subjectId,
                name = name,
                code = code,
                description = description,
                iconKey = iconKey,
                customIconUri = customIconUri,
                accentColorKey = accentColorKey,
                languageMode = languageMode,
                teachingStrategy = teachingStrategy
            ).onSuccess {
                _snackbarMessage.value = "Updated \"$name\"."
            }.onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Failed to update subject."
            }
        }
    }

    fun toggleSubjectEnabled(subjectId: String, enabled: Boolean) {
        viewModelScope.launch {
            repository.toggleSubjectEnabled(currentUserId, subjectId, enabled)
                .onSuccess {
                    _snackbarMessage.value = if (enabled) "Subject enabled." else "Subject hidden from student view."
                }
                .onFailure {
                    _snackbarMessage.value = it.localizedMessage ?: "Failed to update subject state."
                }
        }
    }

    fun deleteSubject(subjectId: String, subjectName: String) {
        viewModelScope.launch {
            repository.deleteSubjectCascade(currentUserId, subjectId)
                .onSuccess {
                    _snackbarMessage.value = "Deleted \"$subjectName\" and its data."
                }
                .onFailure {
                    _snackbarMessage.value = it.localizedMessage ?: "Failed to delete subject."
                }
        }
    }

    fun suggestTeachingStrategy(subjectName: String, onResult: (String) -> Unit) {
        if (subjectName.isBlank()) return
        viewModelScope.launch {
            _aiTaskState.value = AiTaskState.Busy("Drafting FBISE teaching strategy for $subjectName…")
            aiService.generateCustomTeachingStrategy(subjectName)
                .onSuccess { strategy ->
                    _aiTaskState.value = AiTaskState.Idle
                    onResult(strategy)
                }
                .onFailure { e ->
                    _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "Strategy generation failed.")
                }
        }
    }

    // --- MATERIAL UPLOAD & MULTIMODAL OCR / CHAPTER EXTRACTION ---

    fun uploadAndAnalyzeMaterial(
        context: Context,
        subject: Subject,
        uri: Uri,
        selectedCategory: MaterialCategory,
        existingChapters: List<Chapter>
    ) {
        viewModelScope.launch {
            _aiTaskState.value = AiTaskState.Busy("Reading & OCR-scanning uploaded document…")
            try {
                val ingested = DocumentIngestionEngine.ingestFromUri(context, uri, selectedCategory)
                val createRes = repository.createMaterial(
                    userId = currentUserId,
                    subjectId = subject.id,
                    fileName = ingested.fileName,
                    mimeType = ingested.mimeType,
                    category = ingested.inferredCategory,
                    fileSizeBytes = ingested.fileSizeBytes,
                    localUri = ingested.savedLocalPath,
                    pageCount = ingested.pageCount,
                    initialStatus = ProcessingStatus.PROCESSING,
                    statusMessage = "Extracting chapters, topics, formulas & verifying 2027–2028 session…"
                )
                val materialId = createRes.getOrElse { throw it }

                if (ingested.extractedPlainText.isBlank() && ingested.renderedBitmaps.isEmpty()) {
                    repository.updateMaterialAnalysis(
                        userId = currentUserId,
                        materialId = materialId,
                        status = ProcessingStatus.ERROR,
                        statusMessage = ingested.unreadablePagesNote.ifBlank { "No readable text or images could be extracted." },
                        sessionYearDetected = "Unreadable",
                        verifiedOfficialSource = false,
                        extractedSummary = "",
                        extractedText = "",
                        pageCount = ingested.pageCount,
                        unreadablePagesNote = ingested.unreadablePagesNote.ifBlank { "File could not be parsed." }
                    )
                    _aiTaskState.value = AiTaskState.Error(
                        ingested.unreadablePagesNote.ifBlank { "File could not be read. Please upload a clear PDF, DOCX, or page image." }
                    )
                    return@launch
                }

                _aiTaskState.value = AiTaskState.Busy("Analyzing ${ingested.fileName} with Gemini AI…")
                val analysisResult = aiService.analyzeUploadedMaterial(
                    subject = subject,
                    fileName = ingested.fileName,
                    categoryLabel = ingested.inferredCategory.label,
                    extractedPlainText = ingested.extractedPlainText,
                    bitmaps = ingested.renderedBitmaps,
                    localUnreadableNote = ingested.unreadablePagesNote
                )

                analysisResult.onSuccess { analysis ->
                    val status = if (analysis.unreadablePagesReport.isNotBlank()) {
                        ProcessingStatus.PARTIAL_WARNING
                    } else {
                        ProcessingStatus.PROCESSED
                    }
                    val statusMsg = buildString {
                        append("Processed ${ingested.pageCount} page(s). ")
                        if (analysis.chapters.isNotEmpty()) {
                            append("Detected ${analysis.chapters.size} chapter(s). ")
                        }
                        if (analysis.verified20272028) {
                            append("Verified for 2027–2028 session.")
                        } else {
                            append("Session detected: ${analysis.sessionYearDetected}.")
                        }
                    }

                    val combinedText = buildString {
                        if (ingested.extractedPlainText.isNotBlank()) {
                            appendLine(ingested.extractedPlainText.take(60000))
                        }
                        if (analysis.extractedKeyText.isNotBlank()) {
                            appendLine(analysis.extractedKeyText.take(50000))
                        }
                    }.take(115000)

                    repository.updateMaterialAnalysis(
                        userId = currentUserId,
                        materialId = materialId,
                        status = status,
                        statusMessage = statusMsg,
                        sessionYearDetected = analysis.sessionYearDetected,
                        verifiedOfficialSource = analysis.verified20272028 &&
                            (ingested.inferredCategory == MaterialCategory.CURRICULUM_SLO ||
                                ingested.inferredCategory == MaterialCategory.TEXTBOOK ||
                                ingested.inferredCategory == MaterialCategory.MODEL_PAPER ||
                                ingested.inferredCategory == MaterialCategory.PAST_PAPER),
                        extractedSummary = analysis.extractedSummary,
                        extractedText = combinedText,
                        pageCount = ingested.pageCount,
                        unreadablePagesNote = analysis.unreadablePagesReport
                    )

                    // Update subject curriculum verification banner if curriculum/textbook uploaded
                    if (ingested.inferredCategory == MaterialCategory.CURRICULUM_SLO ||
                        ingested.inferredCategory == MaterialCategory.TEXTBOOK ||
                        analysis.verified20272028
                    ) {
                        val warning = if (analysis.verified20272028) {
                            ""
                        } else {
                            analysis.curriculumWarning.ifBlank {
                                "Uploaded document appears to be ${analysis.sessionYearDetected}. Official 2027–2028 FBISE syllabus is still recommended."
                            }
                        }
                        repository.updateSubjectCurriculumStatus(
                            userId = currentUserId,
                            subjectId = subject.id,
                            sessionDetected = analysis.sessionYearDetected,
                            verified = analysis.verified20272028,
                            warning = warning
                        )
                    }

                    // Auto-create newly discovered chapters in original textbook order (without duplicating existing numbers)
                    val existingNumbers = existingChapters.map { it.chapterNumber }.toSet()
                    var createdCount = 0
                    analysis.chapters.sortedBy { it.chapterNumber }.forEach { stub ->
                        if (stub.chapterNumber !in existingNumbers && stub.title.isNotBlank()) {
                            repository.createChapter(
                                userId = currentUserId,
                                subjectId = subject.id,
                                chapterNumber = stub.chapterNumber,
                                title = stub.title,
                                urduTitle = stub.urduTitle,
                                pageReference = stub.pageReference,
                                introduction = stub.introduction,
                                officialSlos = stub.officialSlos,
                                topics = stub.topics,
                                sourceBasis = "Extracted from ${ingested.fileName}"
                            ).onSuccess { createdCount++ }
                        }
                    }

                    _aiTaskState.value = AiTaskState.Idle
                    _snackbarMessage.value = buildString {
                        append("Uploaded ${ingested.fileName}.")
                        if (createdCount > 0) append(" Added $createdCount chapter(s).")
                        if (analysis.unreadablePagesReport.isNotBlank()) append(" Check page warning note.")
                    }
                }.onFailure { err ->
                    repository.updateMaterialAnalysis(
                        userId = currentUserId,
                        materialId = materialId,
                        status = ProcessingStatus.ERROR,
                        statusMessage = err.localizedMessage ?: "AI extraction failed.",
                        sessionYearDetected = "Unverified",
                        verifiedOfficialSource = false,
                        extractedSummary = "",
                        extractedText = ingested.extractedPlainText.take(60000),
                        pageCount = ingested.pageCount,
                        unreadablePagesNote = ingested.unreadablePagesNote
                    )
                    _aiTaskState.value = AiTaskState.Error(err.localizedMessage ?: "Document analysis failed.")
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Upload material failed", e)
                _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "Failed to read selected file.")
            }
        }
    }

    fun deleteMaterial(subjectId: String, materialId: String, fileName: String) {
        viewModelScope.launch {
            repository.deleteMaterial(currentUserId, subjectId, materialId)
                .onSuccess { _snackbarMessage.value = "Removed \"$fileName\"." }
                .onFailure { _snackbarMessage.value = it.localizedMessage ?: "Failed to remove file." }
        }
    }

    // --- CHAPTERS & COMPLETE NOTES GENERATOR ---

    fun addChapterManually(
        subjectId: String,
        chapterNumber: Int,
        title: String,
        urduTitle: String,
        pageReference: String,
        topicsRaw: String,
        slosRaw: String
    ) {
        val topics = topicsRaw.lines().map { it.trim() }.filter { it.isNotEmpty() }
        val slos = slosRaw.lines().map { it.trim() }.filter { it.isNotEmpty() }
        viewModelScope.launch {
            repository.createChapter(
                userId = currentUserId,
                subjectId = subjectId,
                chapterNumber = chapterNumber,
                title = title,
                urduTitle = urduTitle,
                pageReference = pageReference,
                officialSlos = slos,
                topics = topics,
                sourceBasis = "Added by Parent Admin"
            ).onSuccess {
                _snackbarMessage.value = "Added Chapter $chapterNumber: $title."
            }.onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Failed to add chapter."
            }
        }
    }

    fun editChapterMetadata(
        chapterId: String,
        chapterNumber: Int,
        title: String,
        urduTitle: String,
        pageReference: String,
        topicsRaw: String,
        slosRaw: String
    ) {
        val topics = topicsRaw.lines().map { it.trim() }.filter { it.isNotEmpty() }
        val slos = slosRaw.lines().map { it.trim() }.filter { it.isNotEmpty() }
        viewModelScope.launch {
            repository.updateChapterMetadata(
                userId = currentUserId,
                chapterId = chapterId,
                chapterNumber = chapterNumber,
                title = title,
                urduTitle = urduTitle,
                pageReference = pageReference,
                topics = topics,
                officialSlos = slos
            ).onSuccess {
                _snackbarMessage.value = "Saved chapter metadata."
            }.onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Failed to update chapter."
            }
        }
    }

    fun saveChapterSectionEdit(
        chapterId: String,
        sectionKey: String,
        newText: String
    ) {
        viewModelScope.launch {
            val res = when (sectionKey) {
                "completeNotes" -> repository.updateChapterStudyContent(currentUserId, chapterId, completeNotes = newText)
                "shortNotes" -> repository.updateChapterStudyContent(currentUserId, chapterId, shortNotes = newText)
                "definitions" -> repository.updateChapterStudyContent(currentUserId, chapterId, definitions = newText)
                "formulasAndConcepts" -> repository.updateChapterStudyContent(currentUserId, chapterId, formulasAndConcepts = newText)
                "solvedExamples" -> repository.updateChapterStudyContent(currentUserId, chapterId, solvedExamples = newText)
                "solvedExercises" -> repository.updateChapterStudyContent(currentUserId, chapterId, solvedExercises = newText)
                "importantQuestions" -> repository.updateChapterStudyContent(currentUserId, chapterId, importantQuestions = newText)
                "chapterSummary" -> repository.updateChapterStudyContent(currentUserId, chapterId, chapterSummary = newText)
                else -> Result.failure(IllegalArgumentException("Unknown section"))
            }
            res.onSuccess { _snackbarMessage.value = "Saved edits to chapter notes." }
                .onFailure { _snackbarMessage.value = it.localizedMessage ?: "Could not save changes." }
        }
    }

    fun toggleChapterCompleted(subjectId: String, chapter: Chapter, completed: Boolean) {
        viewModelScope.launch {
            repository.toggleChapterCompleted(
                userId = currentUserId,
                subjectId = subjectId,
                chapterId = chapter.id,
                completed = completed,
                currentMastery = chapter.masteryPercent
            ).onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Failed to update chapter progress."
            }
        }
    }

    fun deleteChapter(subjectId: String, chapterId: String, title: String) {
        viewModelScope.launch {
            repository.deleteChapter(currentUserId, subjectId, chapterId)
                .onSuccess { _snackbarMessage.value = "Deleted chapter \"$title\"." }
                .onFailure { _snackbarMessage.value = it.localizedMessage ?: "Failed to delete chapter." }
        }
    }

    fun generateCompleteChapterPackage(
        subject: Subject,
        chapter: Chapter,
        subjectMaterials: List<StudyMaterial>,
        urduEnabled: Boolean
    ) {
        viewModelScope.launch {
            _aiTaskState.value = AiTaskState.Busy("Generating complete bilingual notes & solved exercises for Ch ${chapter.chapterNumber}…")
            aiService.generateChapterNotesPackage(subject, chapter, subjectMaterials, urduEnabled)
                .onSuccess { pkg ->
                    repository.updateChapterStudyContent(
                        userId = currentUserId,
                        chapterId = chapter.id,
                        introduction = pkg.introduction,
                        urduIntroduction = pkg.urduIntroduction,
                        learningObjectives = pkg.learningObjectives,
                        completeNotes = pkg.completeNotes,
                        shortNotes = pkg.shortNotes,
                        definitions = pkg.definitions,
                        formulasAndConcepts = pkg.formulasAndConcepts,
                        solvedExamples = pkg.solvedExamples,
                        solvedExercises = pkg.solvedExercises,
                        importantQuestions = pkg.importantQuestions,
                        chapterSummary = pkg.chapterSummary,
                        sourceBasis = pkg.sourceBasis
                    ).onSuccess {
                        _aiTaskState.value = AiTaskState.Idle
                        _snackbarMessage.value = "Generated complete study package for Chapter ${chapter.chapterNumber}!"
                    }.onFailure { e ->
                        _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "Failed to save generated notes.")
                    }
                }
                .onFailure { e ->
                    _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "Notes generation failed.")
                }
        }
    }

    // --- MCQs & INTERACTIVE TESTS ---

    fun generateMcqBank(
        subject: Subject,
        chapter: Chapter,
        subjectMaterials: List<StudyMaterial>
    ) {
        viewModelScope.launch {
            _aiTaskState.value = AiTaskState.Busy("Generating 30 MCQs, Chapter Test & Answer Key for Ch ${chapter.chapterNumber}…")
            aiService.generateMcqAndTestBank(subject, chapter, subjectMaterials)
                .onSuccess { pkg ->
                    repository.saveMcqBank(
                        userId = currentUserId,
                        subjectId = subject.id,
                        chapterId = chapter.id,
                        chapterTitle = chapter.title,
                        questions = pkg.questions,
                        shortTestQuestions = pkg.shortTestQuestions,
                        longTestQuestions = pkg.longTestQuestions,
                        answerKeySummary = pkg.answerKeySummary
                    ).onSuccess {
                        _aiTaskState.value = AiTaskState.Idle
                        _snackbarMessage.value = "Generated ${pkg.questions.size} MCQs and Chapter Test!"
                    }.onFailure { e ->
                        _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "Failed to save MCQ bank.")
                    }
                }
                .onFailure { e ->
                    _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "MCQ generation failed.")
                }
        }
    }

    fun submitQuizAttempt(
        subject: Subject,
        chapter: Chapter,
        totalQuestions: Int,
        correctAnswers: Int,
        weakTopics: List<String>,
        durationSeconds: Int
    ) {
        viewModelScope.launch {
            repository.recordTestAttempt(
                userId = currentUserId,
                subjectId = subject.id,
                subjectName = subject.name,
                chapterId = chapter.id,
                chapterTitle = chapter.title,
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers,
                weakTopics = weakTopics,
                durationSeconds = durationSeconds
            ).onSuccess {
                val pct = if (totalQuestions > 0) (correctAnswers * 100) / totalQuestions else 0
                _snackbarMessage.value = "Test recorded: $correctAnswers/$totalQuestions ($pct%)."
            }.onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Could not save test result."
            }
        }
    }

    // --- SUBJECT-ISOLATED STREAMING AI TUTOR ---

    fun askSubjectTutor(
        subject: Subject,
        chapter: Chapter?,
        subjectMaterials: List<StudyMaterial>,
        history: List<TutorMessage>,
        question: String,
        urduEnabled: Boolean
    ) {
        val cleanQuestion = question.trim()
        if (cleanQuestion.isEmpty()) return
        viewModelScope.launch {
            repository.addTutorMessage(
                userId = currentUserId,
                subjectId = subject.id,
                chapterId = chapter?.id.orEmpty(),
                role = TutorRole.USER,
                content = cleanQuestion
            ).onFailure {
                _snackbarMessage.value = it.localizedMessage ?: "Failed to send message."
                return@launch
            }

            _aiTaskState.value = AiTaskState.Streaming("")
            var latestReply = ""
            try {
                aiService.streamTutorResponse(
                    subject = subject,
                    chapter = chapter,
                    subjectMaterials = subjectMaterials,
                    history = history,
                    userQuestion = cleanQuestion,
                    urduEnabled = urduEnabled
                ).collect { partial ->
                    latestReply = partial
                    _aiTaskState.value = AiTaskState.Streaming(partial)
                }

                if (latestReply.isNotBlank()) {
                    val splitMarker = "اردو وضاحت:"
                    val (engPart, urduPart) = if (latestReply.contains(splitMarker)) {
                        val idx = latestReply.indexOf(splitMarker)
                        latestReply.substring(0, idx).trim() to latestReply.substring(idx).trim()
                    } else {
                        latestReply.trim() to ""
                    }
                    val sourceNote = if (subjectMaterials.isNotEmpty()) {
                        "Grounded in ${subjectMaterials.size} uploaded ${subject.name} file(s). Verify exam answers against your textbook."
                    } else {
                        "AI Tutor practice explanation (no ${subject.name} textbook uploaded yet)."
                    }
                    repository.addTutorMessage(
                        userId = currentUserId,
                        subjectId = subject.id,
                        chapterId = chapter?.id.orEmpty(),
                        role = TutorRole.TUTOR,
                        content = engPart.ifBlank { latestReply.trim() },
                        urduContent = urduPart,
                        sourceNote = sourceNote
                    )
                    _aiTaskState.value = AiTaskState.Idle
                } else {
                    _aiTaskState.value = AiTaskState.Error("AI Tutor returned an empty response.")
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "AI Tutor streaming failed", e)
                _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "AI Tutor request failed.")
            }
        }
    }

    fun clearSubjectTutorChat(subjectId: String) {
        viewModelScope.launch {
            repository.clearTutorHistory(currentUserId, subjectId)
                .onSuccess { _snackbarMessage.value = "Cleared AI Tutor history for this subject." }
                .onFailure { _snackbarMessage.value = it.localizedMessage ?: "Failed to clear chat." }
        }
    }

    // --- PDF & BACKUP EXPORTS ---

    fun exportChapterPdf(
        context: Context,
        subject: Subject,
        chapter: Chapter,
        mcqBank: McqBank?,
        exportType: PdfExportType,
        onReady: (ExportedPdfResult) -> Unit
    ) {
        viewModelScope.launch {
            _aiTaskState.value = AiTaskState.Busy("Generating A4 PDF: ${exportType.title}…")
            AcademicPdfExporter.exportChapterPdf(context, subject, chapter, mcqBank, exportType)
                .onSuccess { result ->
                    _aiTaskState.value = AiTaskState.Idle
                    _snackbarMessage.value = "Generated ${result.file.name} (${result.pageCount} A4 page(s))."
                    onReady(result)
                }
                .onFailure { e ->
                    _aiTaskState.value = AiTaskState.Error(e.localizedMessage ?: "PDF export failed.")
                }
        }
    }

    fun exportFullBackup(
        context: Context,
        profile: StudentProfile?,
        subjects: List<Subject>,
        chapters: List<Chapter>,
        materials: List<StudyMaterial>,
        attempts: List<TestAttempt>,
        onReady: (Uri) -> Unit
    ) {
        viewModelScope.launch {
            val json = repository.buildBackupJson(profile, subjects, chapters, materials, attempts)
            AcademicPdfExporter.exportBackupJsonFile(context, json)
                .onSuccess { uri ->
                    _snackbarMessage.value = "Backup file ready to save or share."
                    onReady(uri)
                }
                .onFailure { e ->
                    _snackbarMessage.value = e.localizedMessage ?: "Backup export failed."
                }
        }
    }
}
