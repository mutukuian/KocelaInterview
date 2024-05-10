package com.example.kocelainterview.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kocelainterview.presentation.navigation.Screen
import com.example.kocelainterview.presentation.ship_details_screen.ShipDetailScreen
import com.example.kocelainterview.presentation.ships_screen.ShipListScreen
import com.example.kocelainterview.presentation.ui.theme.KocelaInterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KocelaInterviewTheme {
               val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.ShipListScreen.route){
                    composable(route = Screen.ShipListScreen.route){
                        ShipListScreen(navController = navController)
                    }
                    composable(route = Screen.ShipDetailScreen.route + "/{shipId}"){
                        ShipDetailScreen()
                    }
                }
            }
        }
    }
}
