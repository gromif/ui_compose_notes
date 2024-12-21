package com.nevidimka655.notes.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nevidimka655.notes.Notes
import com.nevidimka655.ui.compose_core.theme.spaces

@Preview
@Composable
fun Notes.Note(
    title: String? = "Test title",
    summary: String? = "Test summary...",
    date: String? = "February 6 2023",
    onClick: (() -> Unit) = {}
) = ElevatedCard(
    modifier = Modifier.fillMaxWidth(),
    elevation = CardDefaults.elevatedCardElevation(2.dp)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(MaterialTheme.spaces.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spaces.spaceMedium)
    ) {
        if (title != null) Text(text = title, style = MaterialTheme.typography.titleMedium)
        if (summary != null) Text(text = summary, style = MaterialTheme.typography.bodyMedium)
        if (date != null) Text(text = date, style = MaterialTheme.typography.labelMedium)
    }
}