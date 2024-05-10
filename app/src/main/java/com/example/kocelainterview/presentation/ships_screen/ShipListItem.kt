package com.example.kocelainterview.presentation.ships_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kocelainterview.domain.model.Ship


@Composable
fun ShipListItem(
    modifier:Modifier = Modifier,
    ship: Ship,
    onClick:(()-> Unit)?=null
    //onItemClick: (Ship) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },

        ) {
        AsyncImage(
            modifier = Modifier
                .size(20.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(ship.image).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(20.dp)
        ) {
            Text(
                text = "$ship.ship_name",
                style = MaterialTheme.typography.bodyMedium.copy(),
                //color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$ship.weight_kg",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  //  color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(2.dp))
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_time),
//                    contentDescription = null,
//                    modifier = Modifier.size(SmallIconSize),
//                    tint = colorResource(id = R.color.body)
//                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$ship.year_built",
                    style = MaterialTheme.typography.labelSmall,
                  //  color = colorResource(id = R.color.body)
                )
            }
        }
    }
}



