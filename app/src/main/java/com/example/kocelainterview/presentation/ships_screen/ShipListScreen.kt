package com.example.kocelainterview.presentation.ships_screen


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.presentation.navigation.Screen
import com.example.kocelainterview.presentation.search_controller.search_view_model.SearchViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipListScreen(

    navController: NavController,
    viewModel: ShipListViewModel = hiltViewModel(),
    view:SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value


    var searchText by remember { mutableStateOf("") }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp))
    {

        Text(text = "Ships List", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        //searchbar
        SearchBar(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            searchQuery = searchText,
            onSearchQueryChanged = { searchText = it }, // Update the search query
            onSearchClicked = {
                view.searchShip(searchText)
            }
        )



        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            if (state.isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }

            items(state.ships) { ship ->
                ShipImageCard(ships = ship,
                    onItemClick = {
                    navController.navigate(Screen.ShipDetailScreen.route +"/${ship.ship_id}")
                })
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text =state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
fun ShipImageCard(ships: Ship,onItemClick:(Ship)->Unit){
    val imagePainter = rememberAsyncImagePainter(model = ships.image)
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(16.dp)
            .clickable { onItemClick(ships) }
    ) {
        Box{

            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            Surface(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                modifier = Modifier
                    .align(
                        Alignment.BottomCenter
                    ),
                contentColor = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {

                    Text(text = "Real Name: ${ships.ship_name}")
                    // Spacer(modifier = Modifier.height(2.dp))

                    Text(text = "Ship Status: ${ships.active}")
                    // Spacer(modifier = Modifier.height(2.dp))

                    Text(text = "Weight: ${ships.weight_kg}")
                    //  Spacer(modifier = Modifier.height(2.dp))

                    Text(text = "Year Built: ${ships.year_built}")

                }
            }

        }


    }



}


