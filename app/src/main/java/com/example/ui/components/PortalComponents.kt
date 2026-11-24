package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.data.model.LanguageMode
import com.example.data.model.Subject
import com.example.ui.theme.MonoFontFamily
import com.example.ui.theme.SubjectAccents
import com.example.ui.theme.UrduBodyTextStyle
import com.example.ui.theme.UrduTitleTextStyle

object SubjectIconMapper {
    val options: List<Pair<String, ImageVector>> = listOf(
        "science" to Icons.Filled.Science,
        "calculate" to Icons.Filled.Calculate,
        "bolt" to Icons.Filled.Bolt,
        "biotech" to Icons.Filled.Biotech,
        "computer" to Icons.Filled.Computer,
        "menu_book" to Icons.AutoMirrored.Filled.MenuBook,
        "translate" to Icons.Filled.Translate,
        "mosque" to Icons.Filled.Mosque,
        "public" to Icons.Filled.Public,
        "school" to Icons.Filled.School,
        "history_edu" to Icons.Filled.HistoryEdu,
        "architecture" to Icons.Filled.Architecture,
        "psychology" to Icons.Filled.Psychology
    )

    fun iconFor(key: String): ImageVector =
        options.firstOrNull { it.first == key }?.second ?: Icons.Filled.School
}

@Composable
fun SubjectBadgeIcon(
    iconKey: String,
    customIconUri: String,
    accentColorKey: String,
    contentDescription: String,
    size: Dp = 44.dp
) {
    val accent = SubjectAccents.colorFor(accentColorKey)
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = accent.copy(alpha = 0.14f),
        border = BorderStroke(1.dp, accent.copy(alpha = 0.28f)),
        modifier = Modifier.size(size)
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (customIconUri.isNotBlank()) {
                AsyncImage(
                    model = customIconUri,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(size)
                        .clip(RoundedCornerShape(14.dp))
                )
            } else {
                Icon(
                    imageVector = SubjectIconMapper.iconFor(iconKey),
                    contentDescription = contentDescription,
                    tint = accent,
                    modifier = Modifier.size(size * 0.54f)
                )
            }
        }
    }
}

fun containsUrduCharacters(text: String): Boolean {
    var urduCount = 0
    var latinCount = 0
    for (ch in text) {
        val code = ch.code
        if (code in 0x0600..0x06FF || code in 0x0750..0x077F || code in 0xFB50..0xFDFF || code in 0xFE70..0xFEFF) {
            urduCount++
        } else if (ch in 'A'..'Z' || ch in 'a'..'z') {
            latinCount++
        }
    }
    return urduCount > 0 && urduCount >= latinCount / 2
}

@Composable
fun BilingualTextBlock(
    text: String,
    modifier: Modifier = Modifier,
    isTitle: Boolean = false,
    monospaceHint: Boolean = false
) {
    val lines = remember(text) { text.split("\n") }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        lines.forEach { line ->
            val trimmed = line.trimEnd()
            if (trimmed.isBlank()) {
                Spacer(Modifier.height(4.dp))
            } else if (containsUrduCharacters(trimmed)) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Text(
                        text = trimmed,
                        style = if (isTitle) UrduTitleTextStyle else UrduBodyTextStyle,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                val style = when {
                    isTitle -> MaterialTheme.typography.titleMedium
                    monospaceHint -> MaterialTheme.typography.bodyMedium.copy(fontFamily = MonoFontFamily)
                    else -> MaterialTheme.typography.bodyLarge
                }
                Text(
                    text = trimmed,
                    style = style,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CurriculumStatusPill(
    verified: Boolean,
    sessionLabel: String,
    modifier: Modifier = Modifier
) {
    val bg = if (verified) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
    val fg = if (verified) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = bg,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = if (verified) Icons.Filled.CheckCircle else Icons.Filled.WarningAmber,
                contentDescription = if (verified) "2027-2028 verified" else "Verification needed",
                tint = fg,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = if (verified) "2027–2028 Verified" else sessionLabel.ifBlank { "Upload 2027–2028 SLO" },
                style = MaterialTheme.typography.labelSmall,
                color = fg
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SubjectEditorDialog(
    existingSubject: Subject?,
    onDismiss: () -> Unit,
    onRequestAiStrategy: (String, (String) -> Unit) -> Unit,
    onSave: (
        name: String,
        code: String,
        description: String,
        iconKey: String,
        customIconUri: String,
        accentColorKey: String,
        languageMode: LanguageMode,
        teachingStrategy: String
    ) -> Unit
) {
    var name by remember { mutableStateOf(existingSubject?.name.orEmpty()) }
    var code by remember { mutableStateOf(existingSubject?.code.orEmpty()) }
    var description by remember { mutableStateOf(existingSubject?.description.orEmpty()) }
    var iconKey by remember { mutableStateOf(existingSubject?.iconKey ?: "science") }
    var customIconUri by remember { mutableStateOf(existingSubject?.customIconUri.orEmpty()) }
    var accentKey by remember { mutableStateOf(existingSubject?.accentColorKey ?: "emerald") }
    var languageMode by remember { mutableStateOf(existingSubject?.languageModeEnum ?: LanguageMode.ENGLISH_URDU) }
    var strategy by remember { mutableStateOf(existingSubject?.teachingStrategy.orEmpty()) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            customIconUri = uri.toString()
            iconKey = "custom"
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (existingSubject == null) "Add Class 9 Subject" else "Edit ${existingSubject.name}",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Subject Name *") },
                    placeholder = { Text("e.g., Economics, General Science, Tarjuma-tul-Quran") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("subject_name_input")
                )
                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    label = { Text("Subject Code") },
                    placeholder = { Text("e.g., ECON-9") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Short Description") },
                    minLines = 2,
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Subject Icon", style = MaterialTheme.typography.labelLarge)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    SubjectIconMapper.options.forEach { (key, vector) ->
                        val selected = iconKey == key && customIconUri.isBlank()
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer,
                            border = if (selected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
                            modifier = Modifier
                                .size(48.dp)
                                .clickable {
                                    iconKey = key
                                    customIconUri = ""
                                }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = vector,
                                    contentDescription = key,
                                    tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                OutlinedButton(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Filled.Image, contentDescription = "Pick custom icon", modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(if (customIconUri.isBlank()) "Upload Custom Icon Image" else "Custom Icon Selected (Tap to Change)")
                }

                Text("Accent Color", style = MaterialTheme.typography.labelLarge)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    SubjectAccents.palette.forEach { (key, swatch) ->
                        val selected = accentKey == key
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(swatch)
                                .border(
                                    width = if (selected) 3.dp else 1.dp,
                                    color = if (selected) MaterialTheme.colorScheme.onSurface else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable { accentKey = key }
                        ) {
                            if (selected) {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = "Selected color",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }

                Text("Teaching Language Mode", style = MaterialTheme.typography.labelLarge)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    LanguageMode.entries.forEach { mode ->
                        FilterChip(
                            selected = languageMode == mode,
                            onClick = { languageMode = mode },
                            label = { Text(mode.label) }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("AI Teaching Instructions", style = MaterialTheme.typography.labelLarge)
                    TextButton(
                        onClick = {
                            if (name.isNotBlank()) {
                                onRequestAiStrategy(name.trim()) { suggested -> strategy = suggested }
                            }
                        },
                        enabled = name.isNotBlank()
                    ) {
                        Icon(Icons.Filled.AutoAwesome, contentDescription = "Auto-generate strategy", modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("AI Draft")
                    }
                }
                OutlinedTextField(
                    value = strategy,
                    onValueChange = { strategy = it },
                    placeholder = { Text("Subject-specific teaching style, numerical format, Urdu support…") },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onSave(
                            name.trim(),
                            code.trim(),
                            description.trim(),
                            iconKey,
                            customIconUri,
                            accentKey,
                            languageMode,
                            strategy.trim()
                        )
                    }
                },
                enabled = name.isNotBlank(),
                modifier = Modifier.testTag("save_subject_button")
            ) {
                Text(if (existingSubject == null) "Add Subject" else "Save Changes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

fun shareOrOpenFile(context: Context, uri: Uri, mimeType: String, chooserTitle: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = mimeType
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, chooserTitle))
}
