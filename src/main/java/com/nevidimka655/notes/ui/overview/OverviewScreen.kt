package com.nevidimka655.notes.ui.overview

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nevidimka655.notes.Notes
import com.nevidimka655.ui.compose_core.TextFieldsDefaults
import com.nevidimka655.ui.compose_core.theme.spaces
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlin.compareTo

@Composable
fun Notes.OverviewScreen(
    noteId: Long,
    editMode: Boolean,
    onSaveRequestChannel: Channel<Unit>,
    onDeleteRequestChannel: Channel<Unit>,
    onChangeName: (String) -> Unit,
    onChangeText: (String) -> Unit,
    nameFieldLabel: String,
    textFieldLabel: String
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val vm: OverviewNoteViewModel = hiltViewModel()
    val name by vm.nameState.collectAsStateWithLifecycle()
    val text by vm.textState.collectAsStateWithLifecycle()

    LaunchedEffect(name, text) {
        onChangeName(name)
        onChangeText(text)
    }

    if (editMode) LaunchedEffect(Unit) {
        vm.load(id = noteId)
        onDeleteRequestChannel.receiveAsFlow().collectLatest {
            vm.delete().invokeOnCompletion { backDispatcher?.onBackPressed() }
        }
    }

    LaunchedEffect(Unit) {
        onSaveRequestChannel.receiveAsFlow().collectLatest {
            vm.save().invokeOnCompletion { backDispatcher?.onBackPressed() }
        }
    }

    Overview(
        nameFieldLabel = nameFieldLabel,
        name = name,
        onChangeName = { if (it.length <= 64) vm.setName(name = it) },
        textFieldLabel = textFieldLabel,
        text = text,
        onChangeText = { if (it.length <= 1000) vm.setText(text = it) }
    )
}

@Preview(showBackground = true)
@Composable
private fun Overview(
    nameFieldLabel: String = "Name",
    name: String = "Note name",
    onChangeName: (String) -> Unit = {},
    textFieldLabel: String = "Text",
    text: String = "Note text",
    onChangeText: (String) -> Unit = {}
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
        value = name, onValueChange = onChangeName
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxSize(),
        label = TextFieldsDefaults.labelText(text = textFieldLabel),
        value = text, onValueChange = onChangeText
    )
}