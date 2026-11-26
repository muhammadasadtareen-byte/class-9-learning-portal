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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
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
import androidx.compose.material3.minimumInteractiveComponentSize
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
import com.example.data.model.DefaultFbiseSubjects
import com.example.data.model.LanguageMode
import com.example.data.model.StudentProfile
import com.example.data.model.Subject
import com.example.data.seed.SubjectSeedLoader
import com.example.ui.components.SubjectBadgeIcon
import com.example.ui.components.SubjectEditorDialog
import com.example.ui.theme.SubjectAccents

private val Class9SubjectDisplayOrder = listOf(
    "mathematics",
    "physics",
    "chemistry",
    "biology",
    "computer_science",
    "english",
    "urdu",
    "pakistan_studies",
    "islamiat"
)

fun defaultClass9DashboardSubjects(): List<Subject> {
    val byId = DefaultFbiseSubjects.suggestedSubjects.associateBy { it.idSuffix }
    return Class9SubjectDisplayOrder.mapIndexedNotNull { index, key ->
        val template = byId[key] ?: return@mapIndexedNotNull null
        Subject(
            id = "sub_${template.idSuffix}",
            name = template.name,
            code = template.code,
            description = template.description,
            iconKey = template.iconKey,
            accentColorKey = template.accentColorKey,
            enabled = true,
            languageMode = template.languageMode.name,
            curriculumSession = "Pending 2027-2028 Upload",
            curriculumVerified = false,
            curriculumWarning = DefaultFbiseSubjects.defaultWarningFor(template.name),
            teachingStrategy = template.teachingStrategy,
            sortOrder = index
        )
    }
}

@Composable
fun SubjectListScreen(
    profile: StudentProfile? = null,
    subjects: List<Subject> = defaultClass9DashboardSubjects(),
    allChapters: List<Chapter> = emptyList(),
    onOpenSubject: (Subject) -> Unit = {},
    onResumeChapter: (subjectId: String, chapterId: String) -> Unit = { _, _ -> },
    onAddSubject: (
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String
    ) -> Unit = { _, _, _, _, _, _, _, _ -> },
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
    ) -> Unit = { _, _, _, _, _, _, _, _, _ -> },
    onToggleSubjectEnabled: (subjectId: String, enabled: Boolean) -> Unit = { _, _ -> },
    onDeleteSubject: (subjectId: String, name: String) -> Unit = { _, _ -> },
    onRequestAiStrategy: (String, (String) -> Unit) -> Unit = { _, _ -> }
) {
    var searchQuery by remember { mutableStateOf("") }
    var showDisabled by remember { mutableStateOf(profile?.parentModeEnabled ?: true) }
    var showAddDialog by remember { mutableStateOf(false) }
    var editingSubject by remember { mutableStateOf<Subject?>(null) }
    var deletingSubject by remember { mutableStateOf<Subject?>(null) }

    val parentMode = profile?.parentModeEnabled ?: true
    val effectiveSubjects = remember(subjects) {
        if (subjects.isEmpty()) defaultClass9DashboardSubjects() else subjects
    }

    val filteredSubjects = remember(effectiveSubjects, searchQuery, showDisabled, parentMode) {
        effectiveSubjects.filter { subject ->
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
    val verifiedCount = remember(effectiveSubjects) { effectiveSubjects.count { it.curriculumVerified } }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val columns = when {
            maxWidth >= 960.dp -> 4
            maxWidth >= 600.dp -> 3
            else -> 2
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .testTag("subject_grid")
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

            // Compact Dashboard Overview Metrics
            item(span = { GridItemSpan(columns) }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DashboardMetricChip(
                        label = "Subjects",
                        value = "${effectiveSubjects.count { it.enabled }}",
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.weight(1f)
                    )
                    DashboardMetricChip(
                        label = "Chapters",
                        value = "$completedChapters/$totalChapters",
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.weight(1f)
                    )
                    DashboardMetricChip(
                        label = "Verified",
                        value = "$verifiedCount/${effectiveSubjects.size}",
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Featured Class 9 Science Tutors (Chemistry & Physics SLO Sections)
            val chemistrySubject = effectiveSubjects.firstOrNull {
                it.id == SubjectSeedLoader.CHEM_SUBJECT_ID && (it.enabled || parentMode)
            }
            val physicsSubject = effectiveSubjects.firstOrNull {
                it.id == SubjectSeedLoader.PHY_SUBJECT_ID && (it.enabled || parentMode)
            }
            if (chemistrySubject != null || physicsSubject != null) {
                item(span = { GridItemSpan(columns) }) {
                    ScienceTutorQuickSection(
                        chemistrySubject = chemistrySubject,
                        physicsSubject = physicsSubject,
                        allChapters = allChapters,
                        onOpenSubject = onOpenSubject
                    )
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
                            Spacer(Modifier.width(4.dp))
                            Text(stringResource(R.string.add_subject))
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Class 9 Subjects (${filteredSubjects.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
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

            // Dashboard Subject Grid Tiles
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
                            Icon(
                                imageVector = Icons.Filled.MenuBook,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(36.dp)
                            )
                            Text("No matching subjects", style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Try clearing your search or tap Add Subject to create a new Class 9 course.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(filteredSubjects, key = { it.id }) { subject ->
                    SubjectDashboardGridTile(
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
private fun DashboardMetricChip(
    label: String,
    value: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = containerColor,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = contentColor,
                maxLines = 1
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor.copy(alpha = 0.85f),
                maxLines = 1
            )
        }
    }
}

@Composable
private fun ScienceTutorQuickSection(
    chemistrySubject: Subject?,
    physicsSubject: Subject?,
    allChapters: List<Chapter>,
    onOpenSubject: (Subject) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Science Tutors · FBISE SLO Archive",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Chemistry & Physics",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            chemistrySubject?.let { chem ->
                val chemChapters = allChapters.filter { it.subjectId == chem.id }
                val chemSloCount = chemChapters.sumOf { it.officialSlos.size }
                ScienceTutorTabCard(
                    subject = chem,
                    chapterCount = maxOf(chem.chapterCount, chemChapters.size),
                    sloCount = if (chemSloCount > 0) chemSloCount else 199,
                    tag = "science_tab_chemistry",
                    onClick = { onOpenSubject(chem) },
                    modifier = Modifier.weight(1f)
                )
            }
            physicsSubject?.let { phy ->
                val phyChapters = allChapters.filter { it.subjectId == phy.id }
                val phySloCount = phyChapters.sumOf { it.officialSlos.size }
                ScienceTutorTabCard(
                    subject = phy,
                    chapterCount = maxOf(phy.chapterCount, phyChapters.size),
                    sloCount = if (phySloCount > 0) phySloCount else 141,
                    tag = "science_tab_physics",
                    onClick = { onOpenSubject(phy) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ScienceTutorTabCard(
    subject: Subject,
    chapterCount: Int,
    sloCount: Int,
    tag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accent = SubjectAccents.colorFor(subject.accentColorKey)
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.2.dp, accent.copy(alpha = 0.35f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .minimumInteractiveComponentSize()
            .clickable(onClick = onClick)
            .testTag(tag)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(accent.copy(alpha = 0.12f), Color.Transparent)
                    )
                )
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            SubjectBadgeIcon(
                iconKey = subject.iconKey,
                customIconUri = subject.customIconUri,
                accentColorKey = subject.accentColorKey,
                contentDescription = subject.name,
                size = 38.dp
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = subject.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$chapterCount Ch · $sloCount SLOs",
                    style = MaterialTheme.typography.labelSmall,
                    color = accent,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Open ${subject.name}",
                tint = accent,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun SubjectDashboardGridTile(
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
            color = if (subject.enabled) accent.copy(alpha = 0.24f) else MaterialTheme.colorScheme.outline
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (subject.enabled) 1f else 0.6f)
            .clickable(onClick = onOpen)
            .testTag("subject_card_${subject.code.lowercase().ifBlank { subject.id }}")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            accent.copy(alpha = 0.10f),
                            Color.Transparent
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    SubjectBadgeIcon(
                        iconKey = subject.iconKey,
                        customIconUri = subject.customIconUri,
                        accentColorKey = subject.accentColorKey,
                        contentDescription = subject.name,
                        size = 44.dp
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (subject.code.isNotBlank()) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = accent.copy(alpha = 0.14f)
                            ) {
                                Text(
                                    text = subject.code,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = accent,
                                    maxLines = 1,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                )
                            }
                        }

                        if (parentMode) {
                            Box {
                                IconButton(
                                    onClick = { menuExpanded = true },
                                    modifier = Modifier
                                        .minimumInteractiveComponentSize()
                                        .size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = "Manage ${subject.name}",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(18.dp)
                                    )
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
                }

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = subject.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        minLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = subject.description.ifBlank { subject.languageModeEnum.label },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        minLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = if (subject.curriculumVerified) Icons.Filled.CheckCircle else Icons.Filled.Schedule,
                        contentDescription = if (subject.curriculumVerified) "Verified" else "Ready to upload",
                        tint = if (subject.curriculumVerified) accent else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = if (subject.curriculumVerified) {
                            "Verified · ${subject.chapterCount} Ch"
                        } else {
                            "${subject.chapterCount} Ch · ${subject.materialCount} Files"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    LinearProgressIndicator(
                        progress = { subject.progressFraction },
                        color = accent,
                        trackColor = accent.copy(alpha = 0.14f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(999.dp))
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${(subject.progressFraction * 100).toInt()}% done",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = accent
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Open ${subject.name}",
                            tint = accent,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
        }
    }
}
