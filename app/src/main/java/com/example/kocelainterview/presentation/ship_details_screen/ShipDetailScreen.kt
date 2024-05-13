package com.example.kocelainterview.presentation.ship_details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ShipDetailScreen(
    viewModel: ShipDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box (modifier = Modifier.fillMaxSize()){
        state.ship?.let { shipDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item{
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "$shipDetail.ship_name. $shipDetail.weight_kg ($shipDetail.year_built)",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (shipDetail.active) "active" else "inactive",
                            color = if (shipDetail.active) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "More Details",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "$shipDetail.ship_type " ,
                            fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$shipDetail.home_port",
                            fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                }



            }
        }

        if (state.error.isNotBlank()){
            Text(
                text =state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}