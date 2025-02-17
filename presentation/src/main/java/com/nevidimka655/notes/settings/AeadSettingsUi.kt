package com.nevidimka655.notes.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nevidimka655.astracrypt.resources.R
import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.notes.settings.shared.DatabaseOption
import com.nevidimka655.ui.compose_core.PreferencesGroup
import io.gromif.crypto.tink.model.AeadTemplate

@Composable
internal fun AeadSettingsUi(
    modifier: Modifier = Modifier,
    aeadMode: AeadMode? = AeadMode.None,
    aeadTemplatesList: List<AeadTemplate> = listOf(),
    onNotesAeadChange: (AeadMode) -> Unit = {},
) = Column(modifier = modifier) {
    PreferencesGroup(text = stringResource(id = R.string.notes)) {
        if (aeadMode != null) DatabaseOption(
            aeadMode = aeadMode,
            aeadTemplatesList = aeadTemplatesList,
            onNotesAeadChange = onNotesAeadChange
        )
    }
}