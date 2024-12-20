package com.nevidimka655.notes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nevidimka655.notes.Notes
import com.nevidimka655.ui.compose_core.TextFieldsDefaults
import com.nevidimka655.ui.compose_core.theme.spaces

@Composable
fun Notes.OverviewScreen(
    nameFieldLabel: String,
    name: String,
    onChangeName: (String) -> Unit,
    textFieldLabel: String,
    text: String,
    onChangeText: (String) -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(MaterialTheme.spaces.spaceMedium),
    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spaces.spaceMedium),
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxSize(),
        label = TextFieldsDefaults.labelText(text = nameFieldLabel),
        value = name, onValueChange = onChangeName)
    OutlinedTextField(
        modifier = Modifier.fillMaxSize(),
        label = TextFieldsDefaults.labelText(text = textFieldLabel),
        value = text, onValueChange = onChangeText)
}