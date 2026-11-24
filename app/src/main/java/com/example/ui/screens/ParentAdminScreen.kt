package com.example.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.model.LanguageMode
import com.example.data.model.StudentProfile
import com.example.data.model.Subject
import com.example.ui.components.SubjectBadgeIcon
import com.example.ui.components.SubjectEditorDialog

@Composable
fun ParentAdminScreen(
    profile: StudentProfile?,
    userEmail: String,
    subjects: List<Subject>,
    onUpdateProfile: (studentName: String?, parentMode: Boolean?, urduSupport: Boolean?) -> Unit,
    onAddSubject: (String, String, String, String, String, String, LanguageMode, String) -> Unit,
    onEditSubject: (String, String, String, String, String, String, String, LanguageMode, String) -> Unit,
    onToggleSubjectEnabled: (String, Boolean) -> Unit,
    onDeleteSubject: (String, String) -> Unit,
    onRequestAiStrategy: (String, (String) -> Unit) -> Unit,
    onExportBackup: () -> Unit,
    onSignOut: () -> Unit
) {
    var studentNameDraft by remember(profile?.studentName) {
        mutableStateOf(profile?.studentName ?: "Class 9 Student")
    }
    var showAddDialog by remember { mutableStateOf(false) }
    var editingSubject by remember { mutableStateOf<Subject?>(null) }
    var deletingSubject by remember { mutableStateOf<Subject?>(null) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Parent Administration", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "Single-student configuration · FBISE Class 9 (2027–2028)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Student & Language Settings
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
                    Text("Student & Learning Preferences", style = MaterialTheme.typography.titleMedium)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = studentNameDraft,
                            onValueChange = { studentNameDraft = it },
                            label = { Text("Student Name") },
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = { onUpdateProfile(studentNameDraft.trim(), null, null) },
                            enabled = studentNameDraft.isNotBlank() && studentNameDraft != profile?.studentName
                        ) {
                            Text("Save")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Parent Admin Controls on Home", style = MaterialTheme.typography.titleSmall)
                            Text(
                                "Show edit/delete menus & disabled subjects on the subject grid.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = profile?.parentModeEnabled ?: true,
                            onCheckedChange = { onUpdateProfile(null, it, null) }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("English + Urdu RTL Support", style = MaterialTheme.typography.titleSmall)
                            Text(
                                "Include easy Nastaliq Urdu translations in notes, MCQs, and AI Tutor.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = profile?.urduSupportEnabled ?: true,
                            onCheckedChange = { onUpdateProfile(null, null, it) }
                        )
                    }
                }
            }
        }

        // Dynamic Subject Management
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Manage Subjects (${subjects.size})", style = MaterialTheme.typography.titleLarge)
                Button(onClick = { showAddDialog = true }) {
                    Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Add Subject")
                }
            }
        }

        items(subjects, key = { it.id }) { subject ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    SubjectBadgeIcon(
                        iconKey = subject.iconKey,
                        customIconUri = subject.customIconUri,
                        accentColorKey = subject.accentColorKey,
                        contentDescription = subject.name,
                        size = 40.dp
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(subject.name, style = MaterialTheme.typography.titleSmall)
                        Text(
                            text = "${subject.chapterCount} ch · ${subject.materialCount} files · ${if (subject.enabled) "Enabled" else "Disabled"}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = subject.enabled,
                        onCheckedChange = { onToggleSubjectEnabled(subject.id, it) }
                    )
                    IconButton(onClick = { editingSubject = subject }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit ${subject.name}")
                    }
                    IconButton(onClick = { deletingSubject = subject }) {
                        Icon(Icons.Filled.DeleteOutline, contentDescription = "Delete ${subject.name}", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }

        // Data Backup & Account Security
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Filled.Security, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text("Cloud Security & Backup", style = MaterialTheme.typography.titleMedium)
                    }
                    Text(
                        text = "Authorized Account: ${userEmail.ifBlank { "Google Account" }}\nPrivate Cloud Firestore isolation active — only this authenticated account can read or modify portal data.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Button(
                        onClick = onExportBackup,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("export_backup_button")
                    ) {
                        Icon(Icons.Filled.Backup, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.export_backup))
                    }
                    OutlinedButton(
                        onClick = onSignOut,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("sign_out_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.sign_out))
                    }
                }
            }
        }

        item { Spacer(Modifier.height(72.dp)) }
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
                Text("Deleting \"${target.name}\" permanently removes its chapters, notes, uploaded material records, MCQ banks, and AI Tutor messages.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        deletingSubject = null
                        onDeleteSubject(target.id, target.name)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { deletingSubject = null }) { Text("Cancel") }
            }
        )
    }
}
