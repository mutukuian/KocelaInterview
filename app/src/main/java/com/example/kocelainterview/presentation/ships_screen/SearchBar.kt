package com.example.kocelainterview.presentation.ships_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip

import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchClicked: () -> Unit // Add a new callback for search click
) {
    var searchText by remember { mutableStateOf(searchQuery) } // Remember the search text

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        IconButton(onClick = { onSearchClicked() }) { // Trigger search on click
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Ships")
        }
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it // Update the remembered search text
                onSearchQueryChanged(it) // Notify the listener about the change
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search Ships")
            },
        )
    }
}
