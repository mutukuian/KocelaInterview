package com.example.kocelainterview.presentation.ships_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.kocelainterview.domain.model.Ship


@Composable
fun ShipListScreen(
    ships: LazyPagingItems<Ship>,
    viewModel: ShipListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = ships.loadState) {
        if (ships.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: " + (ships.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        if (ships.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)
            )
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

               items(ships){ship->
                    if (ship != null){
                        ShipListItem(ship = ships, modifier = Modifier.fillMaxWidth())
                    }
                }

                item {
                    if (ships.loadState.append is LoadState.Loading){
                        CircularProgressIndicator()
                    }
                }
                items


            }
        }
    }

}


