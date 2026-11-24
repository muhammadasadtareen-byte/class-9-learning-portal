package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.model.Chapter
import com.example.data.model.LanguageMode
import com.example.data.model.StudentProfile
import com.example.data.model.Subject
import com.example.ui.components.CurriculumStatusPill
import com.example.ui.components.SubjectBadgeIcon
import com.example.ui.components.SubjectEditorDialog
import com.example.ui.theme.SubjectAccents

@Composable
fun HomeSubjectsScreen(
    profile: StudentProfile?,
    subjects: List<Subject>,
    allChapters: List<Chapter>,
    onOpenSubject: (Subject) -> Unit,
    onResumeChapter: (subjectId: String, chapterId: String) -> Unit,
    onAddSubject: (
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String
    ) -> Unit,
    onEditSubject: (
        subjectId: String,
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String
    ) -> Unit,
    onToggleSubjectEnabled: (subjectId: String, enabled: Boolean) -> Unit,
    onDeleteSubject: (subjectId: String, name: String) -> Unit,
    onRequestAiStrategy: (String, (String) -> Unit) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var showDisabled by remember { mutableStateOf(profile?.parentModeEnabled ?: true) }
    var showAddDialog by remember { mutableStateOf(false) }
    var editingSubject by remember { mutableStateOf<Subject?>(null) }
    var deletingSubject by remember { mutableStateOf<Subject?>(null) }

    val parentMode = profile?.parentModeEnabled ?: true

    val filteredSubjects = remember(subjects, searchQuery, showDisabled, parentMode) {
        subjects.filter { subject ->
            val matchesEnabled = if (parentMode && showDisabled) true else subject.enabled
            val q = searchQuery.trim()
            val matchesQuery = q.isEmpty() ||
                subject.name.contains(q, ignoreCase = true) ||
                subject.code.contains(q, ignoreCase = true) ||
                subject.description.contains(q, ignoreCase = true)
            matchesEnabled && matchesQuery
        }
    }

    val totalChapters = remember(allChapters) { allChapters.size }
    val completedChapters = remember(allChapters) { allChapters.count { it.completed } }
    val overallProgress = if (totalChapters == 0) 0f else completedChapters.toFloat() / totalChapters.toFloat()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val columns = when {
            maxWidth >= 960.dp -> 3
            maxWidth >= 600.dp -> 2
            else -> 1
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Editorial Student Hero Card
            item(span = { GridItemSpan(columns) }) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_hero_study_banner_1795424007091),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .matchParentSize()
                                .alpha(0.32f)
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xF00A423F),
                                            Color(0xD80A423F),
                                            Color(0xAA165954)
                                        )
                                    )
                                )
                        )
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${profile?.board ?: "FBISE Pakistan"} · ${profile?.session ?: "2027-2028"}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color(0xFFF2C265)
                                    )
                                    Spacer(Modifier.height(2.dp))
                                    Text(
                                        text = "Assalam-o-Alaikum, ${profile?.studentName ?: "Class 9 Student"}",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = Color.White,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(14.dp),
                                    color = Color(0x2EFFFFFF)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = "${(overallProgress * 100).toInt()}%",
                                            style = MaterialTheme.typography.titleLarge,
                                            color = Color(0xFFF2C265)
                                        )
                                        Text(
                                            text = "Class 9",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color(0xFFDDEEEB)
                                        )
                                    }
                                }
                            }

                            LinearProgressIndicator(
                                progress = { overallProgress },
                                color = Color(0xFFF2C265),
                                trackColor = Color(0x38FFFFFF),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(7.dp)
                                    .clip(RoundedCornerShape(999.dp))
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${subjects.count { it.enabled }} active subjects · $completedChapters/$totalChapters chapters completed",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFFD6EAE7)
                                )
                            }

                            val lastSubId = profile?.lastStudiedSubjectId.orEmpty()
                            val lastChId = profile?.lastStudiedChapterId.orEmpty()
                            val lastTopic = profile?.lastStudiedTopicTitle.orEmpty()
                            if (lastSubId.isNotBlank() && lastChId.isNotBlank()) {
                                Button(
                                    onClick = { onResumeChapter(lastSubId, lastChId) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFF2C265),
                                        contentColor = Color(0xFF1F1602)
                                    ),
                                    shape = RoundedCornerShape(14.dp),
                                    modifier = Modifier.testTag("resume_last_topic_button")
                                ) {
                                    Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(Modifier.width(6.dp))
                                    Text(
                                        text = "Resume: ${lastTopic.ifBlank { "Last Chapter" }}",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Search + Add Subject Controls
            item(span = { GridItemSpan(columns) }) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text(stringResource(R.string.search_subjects_hint)) },
                            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search subjects") },
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("subject_search_input")
                        )
                        Button(
                            onClick = { showAddDialog = true },
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 15.dp),
                            modifier = Modifier.testTag("add_subject_button")
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add Subject")
                            Spacer(Modifier.width(6.dp))
                            Text(stringResource(R.string.add_subject))
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Class 9 Subjects (${filteredSubjects.size})",
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (parentMode) {
                            FilterChip(
                                selected = showDisabled,
                                onClick = { showDisabled = !showDisabled },
                                label = { Text(if (showDisabled) "Showing All" else "Active Only") }
                            )
                        }
                    }
                }
            }

            // Dynamic Subject Cards
            if (filteredSubjects.isEmpty()) {
                item(span = { GridItemSpan(columns) }) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(28.dp)
                        ) {
                            Text("No matching subjects", style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Use Add Subject to create any Class 9 subject and upload its textbooks or SLOs.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(filteredSubjects, key = { it.id }) { subject ->
                    SubjectPortalCard(
                        subject = subject,
                        parentMode = parentMode,
                        onOpen = { onOpenSubject(subject) },
                        onEdit = { editingSubject = subject },
                        onToggleEnabled = { onToggleSubjectEnabled(subject.id, !subject.enabled) },
                        onDelete = { deletingSubject = subject }
                    )
                }
            }

            item(span = { GridItemSpan(columns) }) {
                Spacer(Modifier.height(72.dp))
            }
        }
    }

    if (showAddDialog) {
        SubjectEditorDialog(
            existingSubject = null,
            onDismiss = { showAddDialog = false },
            onRequestAiStrategy = onRequestAiStrategy,
            onSave = { name, code, desc, icon, customUri, accent, lang, strategy ->
                showAddDialog = false
                onAddSubject(name, code, desc, icon, customUri, accent, lang, strategy)
            }
        )
    }

    editingSubject?.let { target ->
        SubjectEditorDialog(
            existingSubject = target,
            onDismiss = { editingSubject = null },
            onRequestAiStrategy = onRequestAiStrategy,
            onSave = { name, code, desc, icon, customUri, accent, lang, strategy ->
                editingSubject = null
                onEditSubject(target.id, name, code, desc, icon, customUri, accent, lang, strategy)
            }
        )
    }

    deletingSubject?.let { target ->
        AlertDialog(
            onDismissRequest = { deletingSubject = null },
            title = { Text("Delete ${target.name}?") },
            text = {
                Text("This permanently deletes \"${target.name}\" along with all its uploaded material records, chapters, notes, MCQ banks, and AI Tutor chat history.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        deletingSubject = null
                        onDeleteSubject(target.id, target.name)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete Subject")
                }
            },
            dismissButton = {
                TextButton(onClick = { deletingSubject = null }) { Text("Cancel") }
            }
        )
    }
}

@Composable
private fun SubjectPortalCard(
    subject: Subject,
    parentMode: Boolean,
    onOpen: () -> Unit,
    onEdit: () -> Unit,
    onToggleEnabled: () -> Unit,
    onDelete: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val accent = SubjectAccents.colorFor(subject.accentColorKey)

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (subject.enabled) MaterialTheme.colorScheme.outlineVariant else MaterialTheme.colorScheme.outline
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (subject.enabled) 1f else 0.62f)
            .clickable(onClick = onOpen)
            .testTag("subject_card_${subject.code.lowercase().ifBlank { subject.id }}")
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubjectBadgeIcon(
                    iconKey = subject.iconKey,
                    customIconUri = subject.customIconUri,
                    accentColorKey = subject.accentColorKey,
                    contentDescription = subject.name,
                    size = 48.dp
                )
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = subject.name,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        if (subject.code.isNotBlank()) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = accent.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = subject.code,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = accent,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                    Text(
                        text = if (!subject.enabled) "Disabled (Admin Only)" else subject.languageModeEnum.label,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (parentMode) {
                    Box {
                        IconButton(onClick = { menuExpanded = true }) {
                            Icon(Icons.Filled.MoreVert, contentDescription = "Manage ${subject.name}")
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Edit Subject") },
                                leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = null) },
                                onClick = {
                                    menuExpanded = false
                                    onEdit()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(if (subject.enabled) "Disable Subject" else "Enable Subject") },
                                leadingIcon = {
                                    Icon(
                                        if (subject.enabled) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    menuExpanded = false
                                    onToggleEnabled()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Delete Subject", color = MaterialTheme.colorScheme.error) },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.DeleteOutline,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                },
                                onClick = {
                                    menuExpanded = false
                                    onDelete()
                                }
                            )
                        }
                    }
                }
            }

            if (subject.description.isNotBlank()) {
                Text(
                    text = subject.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            CurriculumStatusPill(
                verified = subject.curriculumVerified,
                sessionLabel = subject.curriculumSession
            )

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${subject.completedChapterCount}/${subject.chapterCount} Chapters · ${subject.materialCount} Files",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${(subject.progressFraction * 100).toInt()}%",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = accent
                    )
                }
                LinearProgressIndicator(
                    progress = { subject.progressFraction },
                    color = accent,
                    trackColor = accent.copy(alpha = 0.14f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(999.dp))
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Open Workspace",
                    style = MaterialTheme.typography.labelLarge,
                    color = accent
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open ${subject.name}",
                    tint = accent,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
