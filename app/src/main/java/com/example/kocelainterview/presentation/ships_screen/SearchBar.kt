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
import androidx.compose.ui.draw.clip

import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
    ) {
       IconButton(onClick = { /*TODO*/ }) {
           Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Ships")
       }
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                             Text(text = "Search Ships")
                          },
//            leadingIcon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_search),
//                    contentDescription = "Search Icon"
//                )
//            }
        )
    }
}
