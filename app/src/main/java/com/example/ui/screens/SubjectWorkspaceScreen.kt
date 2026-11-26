package com.example.ui.screens

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.model.Chapter
import com.example.data.model.MaterialCategory
import com.example.data.model.McqBank
import com.example.data.model.ProcessingStatus
import com.example.data.model.StudyMaterial
import com.example.data.model.Subject
import com.example.data.model.TutorMessage
import com.example.data.pdf.PdfExportType
import com.example.ui.components.BilingualTextBlock
import com.example.ui.components.CurriculumStatusPill
import com.example.ui.components.SubjectBadgeIcon
import com.example.ui.theme.SubjectAccents
import com.example.ui.viewmodel.AiTaskState
import java.text.SimpleDateFormat
import java.util.Locale

enum class WorkspaceTab(val label: String) {
    OVERVIEW("Dashboard"),
    CHAPTERS("Chapters & Notes"),
    MATERIALS("Materials"),
    MCQS("MCQs & Tests"),
    PDFS("PDF Export"),
    TUTOR("AI Tutor")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectWorkspaceScreen(
    subject: Subject,
    chapters: List<Chapter>,
    materials: List<StudyMaterial>,
    mcqBanks: List<McqBank>,
    tutorMessages: List<TutorMessage>,
    parentMode: Boolean,
    urduEnabled: Boolean,
    aiTaskState: AiTaskState,
    onBack: () -> Unit,
    onOpenChapter: (Chapter) -> Unit,
    onUploadMaterial: (Uri, MaterialCategory) -> Unit,
    onDeleteMaterial: (StudyMaterial) -> Unit,
    onAddChapter: (number: Int, title: String, urduTitle: String, pageRef: String, topicsRaw: String, slosRaw: String) -> Unit,
    onEditChapterMetadata: (chapterId: String, number: Int, title: String, urduTitle: String, pageRef: String, topicsRaw: String, slosRaw: String) -> Unit,
    onToggleChapterCompleted: (Chapter, Boolean) -> Unit,
    onDeleteChapter: (Chapter) -> Unit,
    onGenerateMcqBank: (Chapter) -> Unit,
    onSubmitQuizAttempt: (Chapter, Int, Int, List<String>, Int) -> Unit,
    onSendTutorQuestion: (Chapter?, String) -> Unit,
    onClearTutorChat: () -> Unit,
    onExportPdf: (Chapter, McqBank?, PdfExportType) -> Unit,
    otherScienceSubject: Subject? = null,
    onSwitchSubject: (() -> Unit)? = null
) {
    BackHandler(onBack = onBack)

    var selectedTab by remember { mutableStateOf(WorkspaceTab.OVERVIEW) }
    var selectedUploadCategory by remember { mutableStateOf(MaterialCategory.TEXTBOOK) }
    var showAddChapterDialog by remember { mutableStateOf(false) }
    var editingChapter by remember { mutableStateOf<Chapter?>(null) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            onUploadMaterial(uri, selectedUploadCategory)
        }
    }

    val isAiBusy = aiTaskState is AiTaskState.Busy || aiTaskState is AiTaskState.Streaming

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SubjectBadgeIcon(
                        iconKey = subject.iconKey,
                        customIconUri = subject.customIconUri,
                        accentColorKey = subject.accentColorKey,
                        contentDescription = subject.name,
                        size = 36.dp
                    )
                    Column {
                        Text(
                            text = subject.name,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "${subject.code.ifBlank { "Class 9" }} · ${subject.languageModeEnum.label}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = onBack, modifier = Modifier.testTag("workspace_back_button")) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to subjects")
                }
            },
            actions = {
                if (otherScienceSubject != null && onSwitchSubject != null) {
                    val switchAccent = SubjectAccents.colorFor(otherScienceSubject.accentColorKey)
                    OutlinedButton(
                        onClick = onSwitchSubject,
                        shape = RoundedCornerShape(999.dp),
                        border = BorderStroke(1.dp, switchAccent.copy(alpha = 0.45f)),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .minimumInteractiveComponentSize()
                            .testTag("switch_science_subject_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SwapHoriz,
                            contentDescription = "Switch to ${otherScienceSubject.name}",
                            tint = switchAccent,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = otherScienceSubject.name,
                            style = MaterialTheme.typography.labelMedium,
                            color = switchAccent,
                            maxLines = 1
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTab.ordinal,
            edgePadding = 12.dp,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            WorkspaceTab.entries.forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = { Text(tab.label, style = MaterialTheme.typography.labelLarge) },
                    modifier = Modifier.testTag("workspace_tab_${tab.name.lowercase()}")
                )
            }
        }

        when (selectedTab) {
            WorkspaceTab.OVERVIEW -> SubjectOverviewTab(
                subject = subject,
                chapters = chapters,
                materials = materials,
                onUploadClick = {
                    selectedTab = WorkspaceTab.MATERIALS
                },
                onAddChapterClick = { showAddChapterDialog = true },
                onOpenTutorClick = { selectedTab = WorkspaceTab.TUTOR },
                onOpenChapter = onOpenChapter
            )

            WorkspaceTab.CHAPTERS -> SubjectChaptersTab(
                subject = subject,
                chapters = chapters,
                parentMode = parentMode,
                onOpenChapter = onOpenChapter,
                onAddChapter = { showAddChapterDialog = true },
                onEditChapter = { editingChapter = it },
                onToggleCompleted = onToggleChapterCompleted,
                onDeleteChapter = onDeleteChapter
            )

            WorkspaceTab.MATERIALS -> SubjectMaterialsTab(
                subject = subject,
                materials = materials,
                selectedCategory = selectedUploadCategory,
                isAiBusy = isAiBusy,
                onSelectCategory = { selectedUploadCategory = it },
                onPickDocument = {
                    filePickerLauncher.launch(
                        arrayOf(
                            "application/pdf",
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                            "application/msword",
                            "image/*",
                            "text/plain"
                        )
                    )
                },
                onDeleteMaterial = onDeleteMaterial
            )

            WorkspaceTab.MCQS -> McqAndTestTab(
                subject = subject,
                chapters = chapters,
                mcqBanks = mcqBanks,
                urduEnabled = urduEnabled,
                isAiBusy = isAiBusy,
                onGenerateMcqBank = onGenerateMcqBank,
                onSubmitQuizAttempt = onSubmitQuizAttempt
            )

            WorkspaceTab.PDFS -> SubjectPdfExportTab(
                subject = subject,
                chapters = chapters,
                mcqBanks = mcqBanks,
                isAiBusy = isAiBusy,
                onExportPdf = onExportPdf
            )

            WorkspaceTab.TUTOR -> SubjectTutorTab(
                subject = subject,
                chapters = chapters,
                messages = tutorMessages,
                aiTaskState = aiTaskState,
                materialCount = materials.size,
                onSendQuestion = onSendTutorQuestion,
                onClearChat = onClearTutorChat
            )
        }
    }

    if (showAddChapterDialog) {
        ChapterMetadataDialog(
            existing = null,
            nextChapterNumber = (chapters.maxOfOrNull { it.chapterNumber } ?: 0) + 1,
            onDismiss = { showAddChapterDialog = false },
            onSave = { num, title, urdu, pageRef, topics, slos ->
                showAddChapterDialog = false
                onAddChapter(num, title, urdu, pageRef, topics, slos)
            }
        )
    }

    editingChapter?.let { ch ->
        ChapterMetadataDialog(
            existing = ch,
            nextChapterNumber = ch.chapterNumber,
            onDismiss = { editingChapter = null },
            onSave = { num, title, urdu, pageRef, topics, slos ->
                editingChapter = null
                onEditChapterMetadata(ch.id, num, title, urdu, pageRef, topics, slos)
            }
        )
    }
}

@Composable
private fun SubjectOverviewTab(
    subject: Subject,
    chapters: List<Chapter>,
    materials: List<StudyMaterial>,
    onUploadClick: () -> Unit,
    onAddChapterClick: () -> Unit,
    onOpenTutorClick: () -> Unit,
    onOpenChapter: (Chapter) -> Unit
) {
    val accent = SubjectAccents.colorFor(subject.accentColorKey)
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // Curriculum & SLO Alignment Banner
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (subject.curriculumVerified) {
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
                    } else {
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.72f)
                    }
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CurriculumStatusPill(
                        verified = subject.curriculumVerified,
                        sessionLabel = subject.curriculumSession
                    )
                    Text(
                        text = if (subject.curriculumVerified) {
                            "Verified against uploaded 2027–2028 FBISE ${subject.name} curriculum documents."
                        } else {
                            subject.curriculumWarning.ifBlank {
                                "Official 2027–2028 FBISE ${subject.name} syllabus not yet verified. Upload official textbook or SLO PDFs in Materials."
                            }
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Progress Metrics Row
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Learning Progress", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "${(subject.progressFraction * 100).toInt()}%",
                            style = MaterialTheme.typography.titleMedium,
                            color = accent
                        )
                    }
                    LinearProgressIndicator(
                        progress = { subject.progressFraction },
                        color = accent,
                        trackColor = accent.copy(alpha = 0.14f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(999.dp))
                    )
                    Text(
                        text = "${chapters.count { it.completed }} of ${chapters.size} chapters completed · ${materials.size} uploaded document(s)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = onUploadClick,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.CloudUpload, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Upload Files")
                        }
                        OutlinedButton(
                            onClick = onAddChapterClick,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Add Chapter")
                        }
                    }
                    OutlinedButton(
                        onClick = onOpenTutorClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Filled.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Open ${subject.name} AI Personal Tutor")
                    }
                }
            }
        }

        // Subject-Specific Teaching Strategy
        if (subject.teachingStrategy.isNotBlank()) {
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("${subject.name} Teaching Strategy", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        Text(
                            text = subject.teachingStrategy,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Quick Chapters Preview
        if (chapters.isNotEmpty()) {
            item {
                Text("Chapters in Textbook Order", style = MaterialTheme.typography.titleMedium)
            }
            items(chapters.take(5), key = { it.id }) { ch ->
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenChapter(ch) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Chapter ${ch.chapterNumber}: ${ch.title}", style = MaterialTheme.typography.titleSmall)
                            if (ch.pageReference.isNotBlank()) {
                                Text(ch.pageReference, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Open chapter", modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun SubjectChaptersTab(
    subject: Subject,
    chapters: List<Chapter>,
    parentMode: Boolean,
    onOpenChapter: (Chapter) -> Unit,
    onAddChapter: () -> Unit,
    onEditChapter: (Chapter) -> Unit,
    onToggleCompleted: (Chapter, Boolean) -> Unit,
    onDeleteChapter: (Chapter) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Chapters (${chapters.size})", style = MaterialTheme.typography.titleMedium)
                Button(
                    onClick = onAddChapter,
                    modifier = Modifier.testTag("add_chapter_button")
                ) {
                    Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Add Chapter")
                }
            }
        }

        if (chapters.isEmpty()) {
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(22.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("No ${subject.name} Chapters Yet", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Upload your ${subject.name} PDF textbook or SLO document in the Materials tab to extract chapters automatically in textbook order, or tap \"Add Chapter\" to create one manually.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            items(chapters, key = { it.id }) { chapter ->
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenChapter(chapter) }
                        .testTag("chapter_card_${chapter.chapterNumber}")
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconButton(
                                onClick = { onToggleCompleted(chapter, !chapter.completed) }
                            ) {
                                Icon(
                                    imageVector = if (chapter.completed) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                                    contentDescription = if (chapter.completed) "Completed" else "Mark completed",
                                    tint = if (chapter.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Ch ${chapter.chapterNumber}: ${chapter.title}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                if (chapter.urduTitle.isNotBlank()) {
                                    BilingualTextBlock(text = chapter.urduTitle)
                                }
                                Text(
                                    text = buildString {
                                        if (chapter.pageReference.isNotBlank()) append("${chapter.pageReference} · ")
                                        append("${chapter.topics.size} topics · ")
                                        append(if (chapter.completeNotes.isNotBlank()) "Notes Ready" else "Notes Pending")
                                    },
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (parentMode) {
                                IconButton(onClick = { onEditChapter(chapter) }) {
                                    Icon(Icons.Filled.Edit, contentDescription = "Edit chapter title or topics")
                                }
                                IconButton(onClick = { onDeleteChapter(chapter) }) {
                                    Icon(Icons.Filled.DeleteOutline, contentDescription = "Delete chapter", tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SubjectMaterialsTab(
    subject: Subject,
    materials: List<StudyMaterial>,
    selectedCategory: MaterialCategory,
    isAiBusy: Boolean,
    onSelectCategory: (MaterialCategory) -> Unit,
    onPickDocument: () -> Unit,
    onDeleteMaterial: (StudyMaterial) -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.US) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Upload ${subject.name} Material",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Supports PDF textbooks, scanned PDFs (Gemini Vision OCR), Word/DOCX files, textbook page images, official 2027–2028 SLOs, model papers, past papers, and marking schemes.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        MaterialCategory.entries.forEach { cat ->
                            FilterChip(
                                selected = selectedCategory == cat,
                                onClick = { onSelectCategory(cat) },
                                label = { Text(cat.label, style = MaterialTheme.typography.labelSmall) }
                            )
                        }
                    }
                    Button(
                        onClick = onPickDocument,
                        enabled = !isAiBusy,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("upload_material_button")
                    ) {
                        Icon(Icons.Filled.CloudUpload, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Select ${selectedCategory.label} File")
                    }
                }
            }
        }

        item {
            Text(
                text = "Uploaded ${subject.name} Library (${materials.size})",
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (materials.isEmpty()) {
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("No documents uploaded yet for ${subject.name}", style = MaterialTheme.typography.titleSmall)
                        Text(
                            "Select a document category above and upload your PDF textbook, official 2027–2028 SLO syllabus, past papers, or scanned pages.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            items(materials, key = { it.id }) { mat ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(Icons.Filled.Description, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(mat.fileName, style = MaterialTheme.typography.titleSmall, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                val dateStr = mat.createdAt?.toDate()?.let { dateFormat.format(it) } ?: "Just now"
                                Text(
                                    text = "${mat.categoryEnum.label} · ${mat.pageCount} pg · $dateStr",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(onClick = { onDeleteMaterial(mat) }) {
                                Icon(Icons.Filled.DeleteOutline, contentDescription = "Delete material", tint = MaterialTheme.colorScheme.error)
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(
                                shape = RoundedCornerShape(999.dp),
                                color = when (mat.statusEnum) {
                                    ProcessingStatus.PROCESSED -> MaterialTheme.colorScheme.primaryContainer
                                    ProcessingStatus.PARTIAL_WARNING -> MaterialTheme.colorScheme.secondaryContainer
                                    ProcessingStatus.ERROR -> MaterialTheme.colorScheme.errorContainer
                                    else -> MaterialTheme.colorScheme.surfaceContainer
                                }
                            ) {
                                Text(
                                    text = mat.statusEnum.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(999.dp),
                                color = MaterialTheme.colorScheme.surfaceContainer
                            ) {
                                Text(
                                    text = "Session: ${mat.sessionYearDetected}",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }

                        if (mat.statusMessage.isNotBlank()) {
                            Text(mat.statusMessage, style = MaterialTheme.typography.bodySmall)
                        }

                        if (mat.unreadablePagesNote.isNotBlank()) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    Icon(Icons.Filled.WarningAmber, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Text(
                                        text = mat.unreadablePagesNote,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }

                        if (mat.extractedSummary.isNotBlank()) {
                            Text(
                                text = mat.extractedSummary,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 4,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SubjectPdfExportTab(
    subject: Subject,
    chapters: List<Chapter>,
    mcqBanks: List<McqBank>,
    isAiBusy: Boolean,
    onExportPdf: (Chapter, McqBank?, PdfExportType) -> Unit
) {
    var selectedChapterId by remember(chapters) { mutableStateOf(chapters.firstOrNull()?.id.orEmpty()) }
    val selectedChapter = chapters.firstOrNull { it.id == selectedChapterId } ?: chapters.firstOrNull()
    val bank = mcqBanks.firstOrNull { it.chapterId == selectedChapter?.id }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text("A4 Academic PDF Export Center", style = MaterialTheme.typography.titleLarge)
            Text(
                "Generates A4 print-ready PDFs with academic headers, page numbering, mathematical notation, and Nastaliq Urdu RTL formatting.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (chapters.isEmpty()) {
            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Add or extract at least one ${subject.name} chapter first to export A4 PDFs.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(18.dp)
                    )
                }
            }
        } else {
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    chapters.forEach { ch ->
                        FilterChip(
                            selected = ch.id == selectedChapter?.id,
                            onClick = { selectedChapterId = ch.id },
                            label = { Text("Ch ${ch.chapterNumber}: ${ch.title.take(20)}") }
                        )
                    }
                }
            }

            if (selectedChapter != null) {
                items(PdfExportType.entries) { exportType ->
                    val isPackage = exportType == PdfExportType.FULL_CHAPTER_PACKAGE
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isPackage) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceContainerLowest
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
                        ) {
                            Icon(
                                Icons.Filled.PictureAsPdf,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(exportType.title, style = MaterialTheme.typography.titleSmall)
                                Text(
                                    text = "Ch ${selectedChapter.chapterNumber}: ${selectedChapter.title}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Button(
                                onClick = { onExportPdf(selectedChapter, bank, exportType) },
                                enabled = !isAiBusy,
                                modifier = Modifier.testTag("export_pdf_${exportType.fileSlug}")
                            ) {
                                Text("Export")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChapterMetadataDialog(
    existing: Chapter?,
    nextChapterNumber: Int,
    onDismiss: () -> Unit,
    onSave: (
        number: Int,
        title: String,
        urduTitle: String,
        pageRef: String,
        topicsRaw: String,
        slosRaw: String
    ) -> Unit
) {
    var numberStr by remember { mutableStateOf((existing?.chapterNumber ?: nextChapterNumber).toString()) }
    var title by remember { mutableStateOf(existing?.title.orEmpty()) }
    var urduTitle by remember { mutableStateOf(existing?.urduTitle.orEmpty()) }
    var pageRef by remember { mutableStateOf(existing?.pageReference.orEmpty()) }
    var topicsRaw by remember { mutableStateOf(existing?.topics?.joinToString("\n").orEmpty()) }
    var slosRaw by remember { mutableStateOf(existing?.officialSlos?.joinToString("\n").orEmpty()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (existing == null) "Add Chapter" else "Edit Chapter (OCR / SLO Fix)") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = numberStr,
                    onValueChange = { numberStr = it.filter { ch -> ch.isDigit() } },
                    label = { Text("Chapter Number *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Chapter Title *") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("chapter_title_input")
                )
                OutlinedTextField(
                    value = urduTitle,
                    onValueChange = { urduTitle = it },
                    label = { Text("Urdu Title (Optional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = pageRef,
                    onValueChange = { pageRef = it },
                    label = { Text("Textbook Page Reference") },
                    placeholder = { Text("e.g., pp. 1–24") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = topicsRaw,
                    onValueChange = { topicsRaw = it },
                    label = { Text("Topics & Subtopics (one per line)") },
                    minLines = 3,
                    maxLines = 6,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = slosRaw,
                    onValueChange = { slosRaw = it },
                    label = { Text("Official 2027–2028 SLOs (one per line)") },
                    minLines = 2,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val num = numberStr.toIntOrNull()?.coerceIn(1, 100) ?: nextChapterNumber
                    if (title.isNotBlank()) {
                        onSave(num, title.trim(), urduTitle.trim(), pageRef.trim(), topicsRaw, slosRaw)
                    }
                },
                enabled = title.isNotBlank(),
                modifier = Modifier.testTag("save_chapter_button")
            ) {
                Text(if (existing == null) "Add Chapter" else "Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
