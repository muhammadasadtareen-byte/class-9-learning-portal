package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.core.authStateFlow
import com.example.core.configureAppCheck
import com.example.core.signOutUser
import com.example.data.ai.GeminiAcademicService
import com.example.data.repository.PortalRepository
import com.example.ui.components.shareOrOpenFile
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.ChapterDetailScreen
import com.example.ui.screens.ParentAdminScreen
import com.example.ui.screens.ProgressDashboardScreen
import com.example.ui.screens.SubjectListScreen
import com.example.ui.screens.SubjectWorkspaceScreen
import com.example.ui.theme.Class9PortalTheme
import com.example.ui.viewmodel.AiTaskState
import com.example.ui.viewmodel.DataState
import com.example.ui.viewmodel.PortalViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

private enum class TopDestination(val labelRes: Int, val icon: ImageVector) {
    SUBJECTS(R.string.nav_subjects, Icons.AutoMirrored.Filled.MenuBook),
    PROGRESS(R.string.nav_dashboard, Icons.Filled.Analytics),
    ADMIN(R.string.nav_admin, Icons.Filled.AdminPanelSettings)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // STEP 1: Configure Firebase App Check with launch intent BEFORE any UI or Firebase ops
        configureAppCheck(this, intent)

        enableEdgeToEdge()

        setContent {
            Class9PortalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortalRoot()
                }
            }
        }
    }
}

@Composable
private fun PortalRoot() {
    val auth = Firebase.auth
    val currentUser by auth.authStateFlow().collectAsStateWithLifecycle(initialValue = auth.currentUser)
    val user = currentUser

    if (user == null) {
        AuthScreen(onAuthSuccess = { /* AuthStateListener drives reactive recomposition */ })
    } else {
        key(user.uid) {
            AuthenticatedPortalApp(
                currentUserId = user.uid,
                userDisplayName = user.displayName ?: "Class 9 Student",
                userEmail = user.email.orEmpty()
            )
        }
    }
}

@Composable
private fun AuthenticatedPortalApp(
    currentUserId: String,
    userDisplayName: String,
    userEmail: String
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val credentialManager = remember(context) { CredentialManager.create(context) }

    val viewModel: PortalViewModel = viewModel(
        key = currentUserId,
        factory = viewModelFactory {
            initializer {
                val app = checkNotNull(this[APPLICATION_KEY]) { "APPLICATION_KEY missing" }
                val databaseId = app.getString(R.string.firestore_database_id)
                val db = FirebaseFirestore.getInstance(databaseId)
                PortalViewModel(
                    repository = PortalRepository(db),
                    aiService = GeminiAcademicService(),
                    currentUserId = currentUserId,
                    defaultDisplayName = userDisplayName
                )
            }
        }
    )

    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    val subjectsState by viewModel.subjectsState.collectAsStateWithLifecycle()
    val materialsState by viewModel.materialsState.collectAsStateWithLifecycle()
    val chaptersState by viewModel.chaptersState.collectAsStateWithLifecycle()
    val mcqBanksState by viewModel.mcqBanksState.collectAsStateWithLifecycle()
    val testAttemptsState by viewModel.testAttemptsState.collectAsStateWithLifecycle()
    val tutorMessagesState by viewModel.tutorMessagesState.collectAsStateWithLifecycle()
    val aiTaskState by viewModel.aiTaskState.collectAsStateWithLifecycle()
    val snackbarMessage by viewModel.snackbarMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearSnackbar()
        }
    }

    var currentTopDest by remember { mutableStateOf(TopDestination.SUBJECTS) }
    var activeSubjectId by remember { mutableStateOf<String?>(null) }
    var activeChapterId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(activeSubjectId) {
        viewModel.setActiveTutorSubject(activeSubjectId)
    }

    val profile = (profileState as? DataState.Success)?.data
    val subjects = (subjectsState as? DataState.Success)?.data.orEmpty()
    val allMaterials = (materialsState as? DataState.Success)?.data.orEmpty()
    val allChapters = (chaptersState as? DataState.Success)?.data.orEmpty()
    val allMcqBanks = (mcqBanksState as? DataState.Success)?.data.orEmpty()
    val allAttempts = (testAttemptsState as? DataState.Success)?.data.orEmpty()
    val tutorMessages = (tutorMessagesState as? DataState.Success)?.data.orEmpty()

    val activeSubject = subjects.firstOrNull { it.id == activeSubjectId }
    val activeChapter = allChapters.firstOrNull { it.id == activeChapterId }
    val isInDetailScreen = activeSubject != null

    val firestoreError = listOf(profileState, subjectsState, chaptersState, materialsState)
        .filterIsInstance<DataState.Error>()
        .firstOrNull()?.message

    val isInitialLoading = subjectsState is DataState.Loading && subjects.isEmpty()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useNavigationRail = maxWidth >= 680.dp

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                if (!useNavigationRail && !isInDetailScreen) {
                    NavigationBar {
                        TopDestination.entries.forEach { dest ->
                            NavigationBarItem(
                                selected = currentTopDest == dest,
                                onClick = { currentTopDest = dest },
                                icon = { Icon(dest.icon, contentDescription = stringResource(dest.labelRes)) },
                                label = { Text(stringResource(dest.labelRes)) },
                                modifier = Modifier.testTag("nav_${dest.name.lowercase()}")
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (useNavigationRail && !isInDetailScreen) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Spacer(Modifier.size(16.dp))
                        TopDestination.entries.forEach { dest ->
                            NavigationRailItem(
                                selected = currentTopDest == dest,
                                onClick = { currentTopDest = dest },
                                icon = { Icon(dest.icon, contentDescription = stringResource(dest.labelRes)) },
                                label = { Text(stringResource(dest.labelRes)) },
                                modifier = Modifier.testTag("rail_${dest.name.lowercase()}")
                            )
                        }
                    }
                }

                Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    // Global AI Status / Error Banner
                    when (val ai = aiTaskState) {
                        is AiTaskState.Busy -> {
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                                    ) {
                                        CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                                        Text(
                                            text = ai.label,
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                                }
                            }
                        }

                        is AiTaskState.Error -> {
                            Surface(
                                color = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Icon(Icons.Filled.ErrorOutline, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                                        Text(
                                            text = ai.message,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onErrorContainer
                                        )
                                    }
                                    IconButton(onClick = { viewModel.clearAiError() }) {
                                        Icon(Icons.Filled.Close, contentDescription = "Dismiss error")
                                    }
                                }
                            }
                        }

                        else -> Unit
                    }

                    if (firestoreError != null) {
                        Surface(
                            color = MaterialTheme.colorScheme.errorContainer,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Sync notice: $firestoreError",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                        when {
                            isInitialLoading -> {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator()
                                    Spacer(Modifier.size(12.dp))
                                    Text("Loading your Class 9 Portal…", style = MaterialTheme.typography.bodyMedium)
                                }
                            }

                            activeSubject != null && activeChapter != null -> {
                                val subjectMaterials = allMaterials.filter { it.subjectId == activeSubject.id }
                                val chapterBank = allMcqBanks.firstOrNull { it.chapterId == activeChapter.id }
                                ChapterDetailScreen(
                                    subject = activeSubject,
                                    chapter = activeChapter,
                                    mcqBank = chapterBank,
                                    urduEnabled = profile?.urduSupportEnabled ?: true,
                                    isAiBusy = aiTaskState is AiTaskState.Busy,
                                    onBack = { activeChapterId = null },
                                    onGenerateNotesPackage = {
                                        viewModel.generateCompleteChapterPackage(
                                            subject = activeSubject,
                                            chapter = activeChapter,
                                            subjectMaterials = subjectMaterials,
                                            urduEnabled = profile?.urduSupportEnabled ?: true
                                        )
                                    },
                                    onSaveSectionEdit = { key, text ->
                                        viewModel.saveChapterSectionEdit(activeChapter.id, key, text)
                                    },
                                    onToggleCompleted = { done ->
                                        viewModel.toggleChapterCompleted(activeSubject.id, activeChapter, done)
                                    },
                                    onExportPdf = { exportType ->
                                        viewModel.exportChapterPdf(
                                            context = context,
                                            subject = activeSubject,
                                            chapter = activeChapter,
                                            mcqBank = chapterBank,
                                            exportType = exportType,
                                            onReady = { result ->
                                                shareOrOpenFile(
                                                    context = context,
                                                    uri = result.contentUri,
                                                    mimeType = "application/pdf",
                                                    chooserTitle = "Open or Save ${result.exportType.title}"
                                                )
                                            }
                                        )
                                    }
                                )
                            }

                            activeSubject != null -> {
                                val subjectChapters = allChapters.filter { it.subjectId == activeSubject.id }
                                val subjectMaterials = allMaterials.filter { it.subjectId == activeSubject.id }
                                val subjectBanks = allMcqBanks.filter { it.subjectId == activeSubject.id }

                                SubjectWorkspaceScreen(
                                    subject = activeSubject,
                                    chapters = subjectChapters,
                                    materials = subjectMaterials,
                                    mcqBanks = subjectBanks,
                                    tutorMessages = tutorMessages,
                                    parentMode = profile?.parentModeEnabled ?: true,
                                    urduEnabled = profile?.urduSupportEnabled ?: true,
                                    aiTaskState = aiTaskState,
                                    onBack = { activeSubjectId = null },
                                    onOpenChapter = { ch ->
                                        activeChapterId = ch.id
                                        viewModel.recordResumePoint(activeSubject.id, ch.id, "${activeSubject.name} · Ch ${ch.chapterNumber}: ${ch.title}")
                                    },
                                    onUploadMaterial = { uri, category ->
                                        viewModel.uploadAndAnalyzeMaterial(
                                            context = context,
                                            subject = activeSubject,
                                            uri = uri,
                                            selectedCategory = category,
                                            existingChapters = subjectChapters
                                        )
                                    },
                                    onDeleteMaterial = { mat ->
                                        viewModel.deleteMaterial(activeSubject.id, mat.id, mat.fileName)
                                    },
                                    onAddChapter = { num, title, urdu, pageRef, topicsRaw, slosRaw ->
                                        viewModel.addChapterManually(activeSubject.id, num, title, urdu, pageRef, topicsRaw, slosRaw)
                                    },
                                    onEditChapterMetadata = { chId, num, title, urdu, pageRef, topicsRaw, slosRaw ->
                                        viewModel.editChapterMetadata(chId, num, title, urdu, pageRef, topicsRaw, slosRaw)
                                    },
                                    onToggleChapterCompleted = { ch, done ->
                                        viewModel.toggleChapterCompleted(activeSubject.id, ch, done)
                                    },
                                    onDeleteChapter = { ch ->
                                        viewModel.deleteChapter(activeSubject.id, ch.id, ch.title)
                                    },
                                    onGenerateMcqBank = { ch ->
                                        viewModel.generateMcqBank(activeSubject, ch, subjectMaterials)
                                    },
                                    onSubmitQuizAttempt = { ch, total, correct, weak, sec ->
                                        viewModel.submitQuizAttempt(activeSubject, ch, total, correct, weak, sec)
                                    },
                                    onSendTutorQuestion = { ch, question ->
                                        viewModel.askSubjectTutor(
                                            subject = activeSubject,
                                            chapter = ch,
                                            subjectMaterials = subjectMaterials,
                                            history = tutorMessages,
                                            question = question,
                                            urduEnabled = profile?.urduSupportEnabled ?: true
                                        )
                                    },
                                    onClearTutorChat = {
                                        viewModel.clearSubjectTutorChat(activeSubject.id)
                                    },
                                    onExportPdf = { ch, bank, exportType ->
                                        viewModel.exportChapterPdf(
                                            context = context,
                                            subject = activeSubject,
                                            chapter = ch,
                                            mcqBank = bank,
                                            exportType = exportType,
                                            onReady = { result ->
                                                shareOrOpenFile(
                                                    context = context,
                                                    uri = result.contentUri,
                                                    mimeType = "application/pdf",
                                                    chooserTitle = "Open or Save ${result.exportType.title}"
                                                )
                                            }
                                        )
                                    }
                                )
                            }

                            else -> {
                                when (currentTopDest) {
                                    TopDestination.SUBJECTS -> SubjectListScreen(
                                        profile = profile,
                                        subjects = subjects,
                                        allChapters = allChapters,
                                        onOpenSubject = { sub -> activeSubjectId = sub.id },
                                        onResumeChapter = { subId, chId ->
                                            activeSubjectId = subId
                                            activeChapterId = chId
                                        },
                                        onAddSubject = { name, code, desc, icon, customUri, accent, lang, strategy ->
                                            viewModel.addSubject(name, code, desc, icon, customUri, accent, lang, strategy, subjects.size)
                                        },
                                        onEditSubject = { id, name, code, desc, icon, customUri, accent, lang, strategy ->
                                            viewModel.editSubject(id, name, code, desc, icon, customUri, accent, lang, strategy)
                                        },
                                        onToggleSubjectEnabled = { id, enabled ->
                                            viewModel.toggleSubjectEnabled(id, enabled)
                                        },
                                        onDeleteSubject = { id, name ->
                                            viewModel.deleteSubject(id, name)
                                        },
                                        onRequestAiStrategy = { name, cb ->
                                            viewModel.suggestTeachingStrategy(name, cb)
                                        }
                                    )

                                    TopDestination.PROGRESS -> ProgressDashboardScreen(
                                        profile = profile,
                                        subjects = subjects,
                                        allChapters = allChapters,
                                        testAttempts = allAttempts,
                                        onOpenSubject = { sub -> activeSubjectId = sub.id },
                                        onResumeChapter = { subId, chId ->
                                            activeSubjectId = subId
                                            activeChapterId = chId
                                        }
                                    )

                                    TopDestination.ADMIN -> ParentAdminScreen(
                                        profile = profile,
                                        userEmail = userEmail,
                                        subjects = subjects,
                                        onUpdateProfile = { name, parentMode, urdu ->
                                            viewModel.updateProfileSettings(name, parentMode, urdu)
                                        },
                                        onAddSubject = { name, code, desc, icon, customUri, accent, lang, strategy ->
                                            viewModel.addSubject(name, code, desc, icon, customUri, accent, lang, strategy, subjects.size)
                                        },
                                        onEditSubject = { id, name, code, desc, icon, customUri, accent, lang, strategy ->
                                            viewModel.editSubject(id, name, code, desc, icon, customUri, accent, lang, strategy)
                                        },
                                        onToggleSubjectEnabled = { id, enabled ->
                                            viewModel.toggleSubjectEnabled(id, enabled)
                                        },
                                        onDeleteSubject = { id, name ->
                                            viewModel.deleteSubject(id, name)
                                        },
                                        onRequestAiStrategy = { name, cb ->
                                            viewModel.suggestTeachingStrategy(name, cb)
                                        },
                                        onExportBackup = {
                                            viewModel.exportFullBackup(
                                                context = context,
                                                profile = profile,
                                                subjects = subjects,
                                                chapters = allChapters,
                                                materials = allMaterials,
                                                attempts = allAttempts,
                                                onReady = { uri ->
                                                    shareOrOpenFile(
                                                        context = context,
                                                        uri = uri,
                                                        mimeType = "application/json",
                                                        chooserTitle = "Save or Share Class 9 Portal Backup"
                                                    )
                                                }
                                            )
                                        },
                                        onSignOut = {
                                            signOutUser(credentialManager, scope)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
