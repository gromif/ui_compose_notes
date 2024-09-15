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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.input.TextFieldValue
import com.nevidimka655.notes.Notes
import com.nevidimka655.ui.compose_core.TextFieldsDefaults
import com.nevidimka655.ui.compose_core.theme.spaces

@Composable
fun Notes.OverviewScreen(
    firstFieldLabel: String,
    firstFieldValue: TextFieldValue,
    onFirstFieldValueChange: (TextFieldValue) -> Unit,
    secondFieldLabel: String,
    secondFieldValue: TextFieldValue,
    onSecondFieldValueChange: (TextFieldValue) -> Unit,
) = Column(
    modifier = Modifier
        .nestedScroll(rememberNestedScrollInteropConnection())
        .verticalScroll(rememberScrollState())
        .padding(MaterialTheme.spaces.spaceMedium),
    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spaces.spaceMedium),
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxSize(),
        label = TextFieldsDefaults.labelText(text = firstFieldLabel),
        value = firstFieldValue, onValueChange = onFirstFieldValueChange)
    OutlinedTextField(
        modifier = Modifier.fillMaxSize(),
        label = TextFieldsDefaults.labelText(text = secondFieldLabel),
        value = secondFieldValue, onValueChange = onSecondFieldValueChange)
}