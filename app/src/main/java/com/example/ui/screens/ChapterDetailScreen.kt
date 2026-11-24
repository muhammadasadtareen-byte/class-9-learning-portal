package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.model.Chapter
import com.example.data.model.McqBank
import com.example.data.model.Subject
import com.example.data.pdf.PdfExportType
import com.example.ui.components.BilingualTextBlock

private enum class ChapterSectionTab(val label: String, val fieldKey: String?, val pdfType: PdfExportType) {
    OVERVIEW("Overview & SLOs", null, PdfExportType.FULL_CHAPTER_PACKAGE),
    COMPLETE_NOTES("Complete Notes", "completeNotes", PdfExportType.COMPLETE_NOTES),
    SHORT_NOTES("Short Notes", "shortNotes", PdfExportType.SHORT_NOTES),
    DEFINITIONS("Definitions", "definitions", PdfExportType.DEFINITIONS),
    FORMULAS("Formulas & Concepts", "formulasAndConcepts", PdfExportType.COMPLETE_NOTES),
    EXERCISES("Solved Exercises", "solvedExercises", PdfExportType.SOLVED_EXERCISES),
    IMPORTANT_QS("Important Qs", "importantQuestions", PdfExportType.IMPORTANT_QUESTIONS)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(
    subject: Subject,
    chapter: Chapter,
    mcqBank: McqBank?,
    urduEnabled: Boolean,
    isAiBusy: Boolean,
    onBack: () -> Unit,
    onGenerateNotesPackage: () -> Unit,
    onSaveSectionEdit: (sectionKey: String, newText: String) -> Unit,
    onToggleCompleted: (Boolean) -> Unit,
    onExportPdf: (PdfExportType) -> Unit
) {
    BackHandler(onBack = onBack)

    var selectedTab by remember { mutableStateOf(ChapterSectionTab.OVERVIEW) }
    var editingSectionKey by remember { mutableStateOf<String?>(null) }
    var editingSectionTitle by remember { mutableStateOf("") }
    var editingDraft by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = "Ch ${chapter.chapterNumber}: ${chapter.title}",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${subject.name}${if (chapter.pageReference.isNotBlank()) " · ${chapter.pageReference}" else ""}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onBack, modifier = Modifier.testTag("chapter_back_button")) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to subject")
                }
            },
            actions = {
                IconButton(
                    onClick = { onToggleCompleted(!chapter.completed) },
                    modifier = Modifier.testTag("toggle_chapter_complete_button")
                ) {
                    Icon(
                        imageVector = if (chapter.completed) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                        contentDescription = if (chapter.completed) "Completed" else "Mark chapter complete",
                        tint = if (chapter.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    )
                }
                IconButton(
                    onClick = { onExportPdf(selectedTab.pdfType) },
                    enabled = !isAiBusy,
                    modifier = Modifier.testTag("chapter_quick_pdf_button")
                ) {
                    Icon(Icons.Filled.PictureAsPdf, contentDescription = "Export A4 PDF")
                }
            }
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTab.ordinal,
            edgePadding = 12.dp
        ) {
            ChapterSectionTab.entries.forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = { Text(tab.label, style = MaterialTheme.typography.labelLarge) }
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Action Bar: Generate / Regenerate Complete Notes & Export Full Package
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (chapter.sourceBasis.isNotBlank()) {
                            Text(
                                text = "Source Attribution: ${chapter.sourceBasis}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = onGenerateNotesPackage,
                                enabled = !isAiBusy,
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("generate_chapter_notes_button")
                            ) {
                                Icon(Icons.Filled.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(6.dp))
                                Text(if (chapter.completeNotes.isBlank()) "Generate Notes & Solutions" else "Regenerate Package")
                            }
                            OutlinedButton(
                                onClick = { onExportPdf(PdfExportType.FULL_CHAPTER_PACKAGE) },
                                enabled = !isAiBusy
                            ) {
                                Icon(Icons.Filled.PictureAsPdf, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("Full PDF")
                            }
                        }
                    }
                }
            }

            when (selectedTab) {
                ChapterSectionTab.OVERVIEW -> {
                    item {
                        StudyContentCard(
                            title = "Chapter Introduction",
                            body = buildString {
                                if (chapter.introduction.isNotBlank()) appendLine(chapter.introduction)
                                if (urduEnabled && chapter.urduIntroduction.isNotBlank()) {
                                    appendLine()
                                    appendLine(chapter.urduIntroduction)
                                }
                            }.ifBlank { "Tap \"Generate Notes & Solutions\" above to generate a complete bilingual FBISE Class 9 study guide for this chapter." }
                        )
                    }
                    if (chapter.learningObjectives.isNotEmpty()) {
                        item {
                            StudyContentCard(
                                title = "Learning Objectives",
                                body = chapter.learningObjectives.mapIndexed { i, obj -> "${i + 1}. $obj" }.joinToString("\n")
                            )
                        }
                    }
                    item {
                        StudyContentCard(
                            title = "Official FBISE 2027–2028 SLOs",
                            body = if (chapter.officialSlos.isNotEmpty()) {
                                chapter.officialSlos.joinToString("\n") { "• $it" }
                            } else {
                                "No official 2027–2028 SLO codes extracted yet. Upload the official FBISE ${subject.name} SLO document in Materials (this portal never invents official SLO codes)."
                            }
                        )
                    }
                    if (chapter.topics.isNotEmpty()) {
                        item {
                            StudyContentCard(
                                title = "Topics & Subtopics (Textbook Order)",
                                body = chapter.topics.mapIndexed { i, t -> "${i + 1}. $t" }.joinToString("\n")
                            )
                        }
                    }
                }

                ChapterSectionTab.COMPLETE_NOTES -> {
                    item {
                        StudyContentCard(
                            title = "Complete Detailed Notes",
                            body = chapter.completeNotes.ifBlank { "Not generated yet. Tap \"Generate Notes & Solutions\" above." },
                            onEditClick = {
                                editingSectionKey = "completeNotes"
                                editingSectionTitle = "Edit Complete Notes"
                                editingDraft = chapter.completeNotes
                            }
                        )
                    }
                }

                ChapterSectionTab.SHORT_NOTES -> {
                    item {
                        StudyContentCard(
                            title = "Short Revision Notes",
                            body = chapter.shortNotes.ifBlank { "Not generated yet." },
                            onEditClick = {
                                editingSectionKey = "shortNotes"
                                editingSectionTitle = "Edit Short Revision Notes"
                                editingDraft = chapter.shortNotes
                            }
                        )
                    }
                    item {
                        StudyContentCard(
                            title = "Chapter Summary (English & Urdu)",
                            body = chapter.chapterSummary.ifBlank { "Not generated yet." },
                            onEditClick = {
                                editingSectionKey = "chapterSummary"
                                editingSectionTitle = "Edit Chapter Summary"
                                editingDraft = chapter.chapterSummary
                            }
                        )
                    }
                }

                ChapterSectionTab.DEFINITIONS -> {
                    item {
                        StudyContentCard(
                            title = "Definitions & Bilingual Terminology",
                            body = chapter.definitions.ifBlank { "Not generated yet." },
                            onEditClick = {
                                editingSectionKey = "definitions"
                                editingSectionTitle = "Edit Definitions"
                                editingDraft = chapter.definitions
                            }
                        )
                    }
                }

                ChapterSectionTab.FORMULAS -> {
                    item {
                        StudyContentCard(
                            title = "Formulas, Equations, SI Units & Key Concepts",
                            body = chapter.formulasAndConcepts.ifBlank { "Not generated yet." },
                            monospaceHint = true,
                            onEditClick = {
                                editingSectionKey = "formulasAndConcepts"
                                editingSectionTitle = "Edit Formulas & Concepts"
                                editingDraft = chapter.formulasAndConcepts
                            }
                        )
                    }
                    item {
                        StudyContentCard(
                            title = "Solved Examples (Step-by-Step)",
                            body = chapter.solvedExamples.ifBlank { "Not generated yet." },
                            onEditClick = {
                                editingSectionKey = "solvedExamples"
                                editingSectionTitle = "Edit Solved Examples"
                                editingDraft = chapter.solvedExamples
                            }
                        )
                    }
                }

                ChapterSectionTab.EXERCISES -> {
                    item {
                        StudyContentCard(
                            title = "Solved Textbook Exercises",
                            body = chapter.solvedExercises.ifBlank { "Not generated yet. Upload textbook exercise pages or tap \"Generate Notes & Solutions\"." },
                            onEditClick = {
                                editingSectionKey = "solvedExercises"
                                editingSectionTitle = "Edit Solved Exercises"
                                editingDraft = chapter.solvedExercises
                            }
                        )
                    }
                }

                ChapterSectionTab.IMPORTANT_QS -> {
                    item {
                        StudyContentCard(
                            title = "Important Examination Questions (Short, Long, Conceptual & Application)",
                            body = chapter.importantQuestions.ifBlank { "Not generated yet." },
                            onEditClick = {
                                editingSectionKey = "importantQuestions"
                                editingSectionTitle = "Edit Important Questions"
                                editingDraft = chapter.importantQuestions
                            }
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(64.dp)) }
        }
    }

    if (editingSectionKey != null) {
        AlertDialog(
            onDismissRequest = { editingSectionKey = null },
            title = { Text(editingSectionTitle) },
            text = {
                OutlinedTextField(
                    value = editingDraft,
                    onValueChange = { editingDraft = it },
                    minLines = 8,
                    maxLines = 16,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val key = editingSectionKey
                        editingSectionKey = null
                        if (key != null) onSaveSectionEdit(key, editingDraft)
                    }
                ) {
                    Text("Save Notes")
                }
            },
            dismissButton = {
                TextButton(onClick = { editingSectionKey = null }) { Text("Cancel") }
            }
        )
    }
}

@Composable
private fun StudyContentCard(
    title: String,
    body: String,
    monospaceHint: Boolean = false,
    onEditClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                if (onEditClick != null) {
                    IconButton(onClick = onEditClick) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit $title", modifier = Modifier.size(18.dp))
                    }
                }
            }
            BilingualTextBlock(text = body, monospaceHint = monospaceHint)
        }
    }
}
