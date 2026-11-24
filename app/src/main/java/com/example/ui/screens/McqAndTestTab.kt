package com.example.ui.screens

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.Chapter
import com.example.data.model.McqBank
import com.example.data.model.McqQuestion
import com.example.data.model.Subject
import com.example.ui.components.BilingualTextBlock

private enum class ExamSubTab(val label: String) {
    INTERACTIVE_MCQS("Interactive MCQs"),
    CHAPTER_TEST("Chapter Test"),
    ANSWER_KEY("Answer Key")
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun McqAndTestTab(
    subject: Subject,
    chapters: List<Chapter>,
    mcqBanks: List<McqBank>,
    urduEnabled: Boolean,
    isAiBusy: Boolean,
    onGenerateMcqBank: (Chapter) -> Unit,
    onSubmitQuizAttempt: (
        chapter: Chapter,
        totalQuestions: Int,
        correctAnswers: Int,
        weakTopics: List<String>,
        durationSeconds: Int
    ) -> Unit
) {
    var selectedChapterId by remember(chapters) {
        mutableStateOf(chapters.firstOrNull()?.id.orEmpty())
    }
    val selectedChapter = chapters.firstOrNull { it.id == selectedChapterId } ?: chapters.firstOrNull()
    val bank = mcqBanks.firstOrNull { it.chapterId == selectedChapter?.id }

    var subTab by remember { mutableStateOf(ExamSubTab.INTERACTIVE_MCQS) }
    val selectedAnswers = remember(bank?.id) { mutableStateMapOf<Int, Int>() }
    var submitted by remember(bank?.id) { mutableStateOf(false) }
    var quizStartedAtMs by remember(bank?.id) { mutableLongStateOf(System.currentTimeMillis()) }
    var lastCorrectCount by remember(bank?.id) { mutableIntStateOf(0) }
    var lastWeakTopics by remember(bank?.id) { mutableStateOf<List<String>>(emptyList()) }

    if (chapters.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.Quiz,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(44.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text("No Chapters Yet", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(6.dp))
            Text(
                "Upload a ${subject.name} textbook or add a chapter first to generate 30+ MCQs, chapter tests, and answer keys.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // Chapter selector row
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Select Chapter", style = MaterialTheme.typography.labelLarge)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    chapters.forEach { ch ->
                        FilterChip(
                            selected = ch.id == selectedChapter?.id,
                            onClick = { selectedChapterId = ch.id },
                            label = { Text("Ch ${ch.chapterNumber}: ${ch.title.take(22)}") }
                        )
                    }
                }
            }
        }

        // Generator & Mode Switcher
        if (selectedChapter != null) {
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
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Ch ${selectedChapter.chapterNumber}: ${selectedChapter.title}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = if (bank == null) "Not generated yet"
                                    else "${bank.questions.size} MCQs · ${bank.shortTestQuestions.size} Short · ${bank.longTestQuestions.size} Long",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Button(
                                onClick = { onGenerateMcqBank(selectedChapter) },
                                enabled = !isAiBusy,
                                modifier = Modifier.testTag("generate_mcqs_button")
                            ) {
                                Icon(Icons.Filled.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(6.dp))
                                Text(if (bank == null) "Generate 30 MCQs" else "Regenerate")
                            }
                        }

                        if (bank != null) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.horizontalScroll(rememberScrollState())
                            ) {
                                ExamSubTab.entries.forEach { tab ->
                                    FilterChip(
                                        selected = subTab == tab,
                                        onClick = { subTab = tab },
                                        label = { Text(tab.label) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (bank == null) {
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
                        Text("FBISE 30-MCQ & Chapter Test Generator", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Tap \"Generate 30 MCQs\" to create 30 chapter MCQs with 4 options, Urdu hints, explanations, a printable Chapter Test (short + long questions), and a separate Answer Key.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            when (subTab) {
                ExamSubTab.INTERACTIVE_MCQS -> {
                    if (submitted) {
                        item {
                            val total = bank.questions.size.coerceAtLeast(1)
                            val pct = (lastCorrectCount * 100) / total
                            Card(
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (pct >= 80) MaterialTheme.colorScheme.primaryContainer
                                    else MaterialTheme.colorScheme.secondaryContainer
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = "Test Result: $lastCorrectCount / $total ($pct%)",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                    if (lastWeakTopics.isNotEmpty()) {
                                        Text(
                                            text = "Weak Topics to Revise:",
                                            style = MaterialTheme.typography.labelLarge
                                        )
                                        FlowRow(
                                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                                            verticalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            lastWeakTopics.forEach { topic ->
                                                Surface(
                                                    shape = RoundedCornerShape(999.dp),
                                                    color = MaterialTheme.colorScheme.errorContainer
                                                ) {
                                                    Text(
                                                        text = topic,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = MaterialTheme.colorScheme.onErrorContainer,
                                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                                    )
                                                }
                                            }
                                        }
                                    } else {
                                        Text(
                                            text = "Excellent mastery! No weak topics detected in this chapter.",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            selectedAnswers.clear()
                                            submitted = false
                                            quizStartedAtMs = System.currentTimeMillis()
                                        }
                                    ) {
                                        Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(Modifier.width(6.dp))
                                        Text("Retake MCQ Test")
                                    }
                                }
                            }
                        }
                    }

                    itemsIndexed(bank.questions, key = { idx, q -> "${bank.id}_${q.number}_$idx" }) { idx, mcq ->
                        McqQuestionCard(
                            question = mcq,
                            selectedOption = selectedAnswers[idx],
                            showSolutions = submitted,
                            urduEnabled = urduEnabled,
                            onSelectOption = { optIdx ->
                                if (!submitted) selectedAnswers[idx] = optIdx
                            }
                        )
                    }

                    if (!submitted && selectedChapter != null) {
                        item {
                            Button(
                                onClick = {
                                    var correct = 0
                                    val weak = mutableListOf<String>()
                                    bank.questions.forEachIndexed { idx, q ->
                                        val chosen = selectedAnswers[idx]
                                        if (chosen == q.correctIndex) {
                                            correct++
                                        } else {
                                            weak.add(q.topicTag.ifBlank { selectedChapter.title })
                                        }
                                    }
                                    val elapsedSec = ((System.currentTimeMillis() - quizStartedAtMs) / 1000L).toInt().coerceAtLeast(1)
                                    lastCorrectCount = correct
                                    lastWeakTopics = weak.distinct().take(12)
                                    submitted = true
                                    onSubmitQuizAttempt(
                                        selectedChapter,
                                        bank.questions.size,
                                        correct,
                                        lastWeakTopics,
                                        elapsedSec
                                    )
                                },
                                enabled = selectedAnswers.isNotEmpty(),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .testTag("submit_mcq_test_button")
                            ) {
                                Text("Submit Test (${selectedAnswers.size}/${bank.questions.size} Answered)")
                            }
                        }
                    }
                }

                ExamSubTab.CHAPTER_TEST -> {
                    item {
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text("Section B — Short Questions", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                                bank.shortTestQuestions.forEachIndexed { idx, sq ->
                                    BilingualTextBlock(text = "${idx + 1}. $sq")
                                }
                                Spacer(Modifier.height(6.dp))
                                Text("Section C — Long / Extensive Questions", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                                bank.longTestQuestions.forEachIndexed { idx, lq ->
                                    BilingualTextBlock(text = "${idx + 1}. $lq")
                                }
                            }
                        }
                    }
                }

                ExamSubTab.ANSWER_KEY -> {
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
                                Text("Separate Objective Answer Key", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                                val letters = listOf("A", "B", "C", "D")
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    bank.questions.forEach { q ->
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = MaterialTheme.colorScheme.surfaceContainerLow
                                        ) {
                                            Text(
                                                text = "Q${q.number}: ${letters.getOrElse(q.correctIndex) { "A" }}",
                                                style = MaterialTheme.typography.labelMedium,
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                            )
                                        }
                                    }
                                }
                                if (bank.answerKeySummary.isNotBlank()) {
                                    Spacer(Modifier.height(6.dp))
                                    Text("Detailed Key & Marking Notes", style = MaterialTheme.typography.titleMedium)
                                    BilingualTextBlock(text = bank.answerKeySummary)
                                }
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(64.dp)) }
    }
}

@Composable
private fun McqQuestionCard(
    question: McqQuestion,
    selectedOption: Int?,
    showSolutions: Boolean,
    urduEnabled: Boolean,
    onSelectOption: (Int) -> Unit
) {
    val letters = listOf("A", "B", "C", "D")
    val isOfficial = question.sourceType == "VERIFIED_OFFICIAL"

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
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "Q${question.number} · ${question.topicTag.take(24)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = if (isOfficial) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surfaceContainer
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        if (isOfficial) {
                            Icon(Icons.Filled.Verified, contentDescription = null, modifier = Modifier.size(12.dp))
                        }
                        Text(
                            text = if (isOfficial) "Verified Official" else "AI Practice",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }

            BilingualTextBlock(text = question.question, isTitle = true)

            if (urduEnabled && question.urduHint.isNotBlank()) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BilingualTextBlock(
                        text = question.urduHint,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                question.options.take(4).forEachIndexed { idx, optText ->
                    val isSelected = selectedOption == idx
                    val isCorrect = idx == question.correctIndex
                    val borderColor = when {
                        showSolutions && isCorrect -> Color(0xFF1F7A4D)
                        showSolutions && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                        isSelected -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.outlineVariant
                    }
                    val bgColor = when {
                        showSolutions && isCorrect -> Color(0x1F1F7A4D)
                        showSolutions && isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.55f)
                        isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                        else -> MaterialTheme.colorScheme.surface
                    }
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = bgColor,
                        border = BorderStroke(if (isSelected || (showSolutions && isCorrect)) 1.5.dp else 1.dp, borderColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !showSolutions) { onSelectOption(idx) }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = "${letters.getOrElse(idx) { "?" }}.",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = borderColor
                            )
                            Text(
                                text = optText,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                            if (showSolutions && isCorrect) {
                                Icon(Icons.Filled.CheckCircle, contentDescription = "Correct", tint = Color(0xFF1F7A4D), modifier = Modifier.size(18.dp))
                            } else if (showSolutions && isSelected && !isCorrect) {
                                Icon(Icons.Filled.Close, contentDescription = "Incorrect", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }
            }

            if (showSolutions && question.explanation.isNotBlank()) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Explanation (${question.pageRef.ifBlank { "Concept" }})",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        BilingualTextBlock(text = question.explanation)
                    }
                }
            }
        }
    }
}
