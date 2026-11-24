package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.Chapter
import com.example.data.model.StudentProfile
import com.example.data.model.Subject
import com.example.data.model.TestAttempt
import com.example.ui.components.SubjectBadgeIcon
import com.example.ui.theme.SubjectAccents
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProgressDashboardScreen(
    profile: StudentProfile?,
    subjects: List<Subject>,
    allChapters: List<Chapter>,
    testAttempts: List<TestAttempt>,
    onOpenSubject: (Subject) -> Unit,
    onResumeChapter: (subjectId: String, chapterId: String) -> Unit
) {
    val activeSubjects = remember(subjects) { subjects.filter { it.enabled } }
    val totalChapters = allChapters.size
    val completedChapters = allChapters.count { it.completed }
    val remainingChapters = (totalChapters - completedChapters).coerceAtLeast(0)
    val overallPct = if (totalChapters == 0) 0 else (completedChapters * 100) / totalChapters
    val avgScore = remember(testAttempts) {
        if (testAttempts.isEmpty()) 0 else testAttempts.map { it.percentage }.average().toInt()
    }

    val weakTopicCounts = remember(testAttempts) {
        testAttempts.flatMap { it.weakTopics }
            .groupingBy { it }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(10)
    }

    val dateFormat = remember { SimpleDateFormat("dd MMM, HH:mm", Locale.US) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Personal Progress Dashboard", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "${profile?.studentName ?: "Class 9 Student"} · FBISE Pakistan (${profile?.session ?: "2027-2028"})",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Summary KPI Row
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MetricTile(
                    label = "Overall Progress",
                    value = "$overallPct%",
                    detail = "$completedChapters/$totalChapters Ch",
                    modifier = Modifier.weight(1f)
                )
                MetricTile(
                    label = "Remaining",
                    value = "$remainingChapters",
                    detail = "Chapters left",
                    modifier = Modifier.weight(1f)
                )
                MetricTile(
                    label = "Test Average",
                    value = if (testAttempts.isEmpty()) "—" else "$avgScore%",
                    detail = "${testAttempts.size} attempt(s)",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Resume Card
        val lastSubId = profile?.lastStudiedSubjectId.orEmpty()
        val lastChId = profile?.lastStudiedChapterId.orEmpty()
        if (lastSubId.isNotBlank() && lastChId.isNotBlank()) {
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Recently Studied", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            Text(
                                text = profile?.lastStudiedTopicTitle.orEmpty().ifBlank { "Resume Chapter" },
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Button(onClick = { onResumeChapter(lastSubId, lastChId) }) {
                            Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Resume")
                        }
                    }
                }
            }
        }

        // Weak Topics & Revision Recommendations
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
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            if (weakTopicCounts.isEmpty()) Icons.Filled.Lightbulb else Icons.Filled.WarningAmber,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Text("Weak Topics & Revision Recommendations", style = MaterialTheme.typography.titleMedium)
                    }
                    if (weakTopicCounts.isEmpty()) {
                        Text(
                            text = "Attempt chapter MCQ tests to identify weak topics automatically and receive focused revision targets.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            weakTopicCounts.forEach { (topic, misses) ->
                                Surface(
                                    shape = RoundedCornerShape(999.dp),
                                    color = MaterialTheme.colorScheme.errorContainer
                                ) {
                                    Text(
                                        text = "$topic ($misses×)",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onErrorContainer,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                    )
                                }
                            }
                        }
                        val topWeak = weakTopicCounts.first().key
                        Text(
                            text = "Recommendation: Re-read Short Revision Notes for \"$topWeak\", ask your subject AI Tutor for 5 focused examples, and re-attempt the chapter quiz.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Subject-Wise Progress & Percentages
        item {
            Text("Subject-Wise Breakdown", style = MaterialTheme.typography.titleLarge)
        }

        items(activeSubjects, key = { it.id }) { subject ->
            val accent = SubjectAccents.colorFor(subject.accentColorKey)
            val subAttempts = testAttempts.filter { it.subjectId == subject.id }
            val subAvg = if (subAttempts.isEmpty()) null else subAttempts.map { it.percentage }.average().toInt()
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                onClick = { onOpenSubject(subject) }
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        SubjectBadgeIcon(
                            iconKey = subject.iconKey,
                            customIconUri = subject.customIconUri,
                            accentColorKey = subject.accentColorKey,
                            contentDescription = subject.name,
                            size = 40.dp
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(subject.name, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "${subject.completedChapterCount}/${subject.chapterCount} chapters · ${subject.materialCount} material(s)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${(subject.progressFraction * 100).toInt()}%",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = accent
                            )
                            Text(
                                text = if (subAvg != null) "Avg: $subAvg%" else "No tests",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
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
            }
        }

        // Recent Test History
        if (testAttempts.isNotEmpty()) {
            item {
                Text("Recent Chapter Test Marks", style = MaterialTheme.typography.titleLarge)
            }
            items(testAttempts.take(15), key = { it.id }) { attempt ->
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.EmojiEvents, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                            Column {
                                Text("${attempt.subjectName} — ${attempt.chapterTitle}", style = MaterialTheme.typography.titleSmall)
                                val dateStr = attempt.createdAt?.toDate()?.let { dateFormat.format(it) } ?: "Today"
                                Text(
                                    text = "${attempt.correctAnswers}/${attempt.totalQuestions} correct · $dateStr",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Surface(
                            shape = RoundedCornerShape(999.dp),
                            color = if (attempt.percentage >= 80) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = "${attempt.percentage}%",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(72.dp)) }
    }
}

@Composable
private fun MetricTile(
    label: String,
    value: String,
    detail: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Text(detail, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
