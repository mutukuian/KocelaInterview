package com.example.kocelainterview.presentation.ships_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.kocelainterview.domain.model.Ship

@Composable
fun ShipListItem(
    ship:Ship,
    onItemClick:(Ship) -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(ship) }
            .padding(20.dp),
        horizontalArrangement =Arrangement.SpaceBetween
    ){

        Text(
            text = "${ship.ship_name}. ${ship.weight_kg} (${ship.year_built})",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = if(ship.active) "active" else "inactive",
            color = if (ship.active) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(CenterVertically)
        )

    }
}