package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.model.Chapter
import com.example.data.model.Subject
import com.example.data.model.TutorMessage
import com.example.data.model.TutorRole
import com.example.ui.components.BilingualTextBlock
import com.example.ui.viewmodel.AiTaskState

private val quickTutorPrompts = listOf(
    "Explain this topic in simple English",
    "Translate this explanation into Urdu",
    "Solve this exercise step by step",
    "Give me more examples",
    "Ask me five practice questions",
    "Explain why my answer is wrong",
    "Prepare me for my chapter test"
)

@Composable
fun SubjectTutorTab(
    subject: Subject,
    chapters: List<Chapter>,
    messages: List<TutorMessage>,
    aiTaskState: AiTaskState,
    materialCount: Int,
    onSendQuestion: (chapter: Chapter?, question: String) -> Unit,
    onClearChat: () -> Unit
) {
    var selectedChapterId by remember { mutableStateOf<String?>(null) }
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val focusedChapter = chapters.firstOrNull { it.id == selectedChapterId }
    val isStreaming = aiTaskState is AiTaskState.Streaming
    val streamingText = (aiTaskState as? AiTaskState.Streaming)?.partialText.orEmpty()

    LaunchedEffect(messages.size, streamingText.length) {
        val totalItems = messages.size + if (isStreaming) 1 else 0
        if (totalItems > 0) {
            listState.animateScrollToItem(totalItems)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Tutor Context Bar (Strictly isolated to this subject)
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${subject.name} AI Personal Tutor",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = if (materialCount > 0) "Using $materialCount uploaded ${subject.name} file(s) first · Isolated context"
                            else "No ${subject.name} files uploaded yet · Practice tutoring mode",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    if (messages.isNotEmpty()) {
                        TextButton(onClick = onClearChat) {
                            Icon(Icons.Filled.DeleteSweep, contentDescription = "Clear chat", modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Clear")
                        }
                    }
                }

                if (chapters.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        FilterChip(
                            selected = selectedChapterId == null,
                            onClick = { selectedChapterId = null },
                            label = { Text("All ${subject.name}") }
                        )
                        chapters.forEach { ch ->
                            FilterChip(
                                selected = selectedChapterId == ch.id,
                                onClick = { selectedChapterId = ch.id },
                                label = { Text("Ch ${ch.chapterNumber}") }
                            )
                        }
                    }
                }
            }
        }

        // Chat Feed
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            if (messages.isEmpty() && !isStreaming) {
                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Icon(Icons.Filled.AutoAwesome, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                Text("Ask Your ${subject.name} Tutor", style = MaterialTheme.typography.titleMedium)
                            }
                            Text(
                                "This tutor is dedicated exclusively to Class 9 ${subject.name}. Ask for simple English explanations, easy Urdu translations, step-by-step numericals, or a 5-question viva.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            items(messages, key = { it.id }) { msg ->
                TutorMessageBubble(message = msg)
            }

            if (isStreaming) {
                item(key = "streaming_bubble") {
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "${subject.name} Tutor is writing…",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            BilingualTextBlock(
                                text = streamingText.ifBlank { "Thinking through your ${subject.name} materials…" }
                            )
                        }
                    }
                }
            }
        }

        // Quick Student Prompts
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            quickTutorPrompts.forEach { prompt ->
                AssistChip(
                    onClick = {
                        if (!isStreaming) {
                            onSendQuestion(focusedChapter, prompt)
                        }
                    },
                    label = { Text(prompt, style = MaterialTheme.typography.labelMedium) }
                )
            }
        }

        // Composer
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLowest,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Ask about ${focusedChapter?.title ?: subject.name}…") },
                    maxLines = 4,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("tutor_question_input")
                )
                IconButton(
                    onClick = {
                        val q = inputText.trim()
                        if (q.isNotEmpty() && !isStreaming) {
                            inputText = ""
                            onSendQuestion(focusedChapter, q)
                        }
                    },
                    enabled = inputText.isNotBlank() && !isStreaming,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("tutor_send_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send question")
                }
            }
        }
    }
}

@Composable
private fun TutorMessageBubble(message: TutorMessage) {
    val isUser = message.roleEnum == TutorRole.USER
    val containerColor = if (isUser) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceContainerLowest
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isUser) 18.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 18.dp
            ),
            colors = CardDefaults.cardColors(containerColor = containerColor),
            border = if (isUser) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = Modifier.fillMaxWidth(if (isUser) 0.86f else 0.96f)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isUser) {
                    Text(
                        text = message.content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    BilingualTextBlock(text = message.content)
                    if (message.urduContent.isNotBlank()) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceContainerLow,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            BilingualTextBlock(
                                text = message.urduContent,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                    if (message.sourceNote.isNotBlank()) {
                        Text(
                            text = message.sourceNote,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
