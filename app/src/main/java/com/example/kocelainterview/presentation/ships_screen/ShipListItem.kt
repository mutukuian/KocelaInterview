package com.example.kocelainterview.presentation.ships_screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.example.kocelainterview.domain.model.Ship


@Composable
fun ShipListItem(
    modifier:Modifier = Modifier,
    ship: Ship,
) {
    Card(
        modifier = modifier,
        //elevation = 4.dp

    ) {
        Row (
            modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(16.dp)
        ){
           AsyncImage(
               model = ship.image,
               contentDescription = ship.ship_name,
               modifier = Modifier
                   .weight(1f)
                   .height(150.dp)
           )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "ShipName: ${ship.ship_name}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

               Text(
                   text = "YearBuilt: ${ship.year_built}",
                   style = MaterialTheme.typography.bodyMedium,
                   modifier = Modifier.fillMaxWidth()
               )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Active Status: ${ship.active}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Weight in KG: ${ship.weight_kg}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



