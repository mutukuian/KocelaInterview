package com.example.kocelainterview

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.presentation.ships_screen.ShipListScreen
import com.example.kocelainterview.presentation.ships_screen.ShipListState
import com.example.kocelainterview.presentation.ships_screen.ShipListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ShipListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var mockViewModel: ShipListViewModel

    @Before
    fun setUp() {
        mockViewModel = mockk(relaxed = true, relaxUnitFun = true)
        hiltRule.inject()
    }




    @Test
    fun shipListScreen_displaysLoadingIndicator_whenLoading() {
        every { mockViewModel.state } returns mutableStateOf(ShipListState(isLoading = true))

        composeTestRule.setContent {
            ShipListScreen(navController = rememberNavController(), viewModel = mockViewModel)
        }

        composeTestRule.onNodeWithTag("loadingIndicator").assertExists()
    }

    @Test
    fun shipListScreen_displaysShips_whenLoaded() {
        val ships = listOf(
            Ship(
                ship_id = "1",
                ship_name = "Titanic",
                active = true,
                weight_kg = 50000,
                year_built = 1912,
                image = ""
            )
        )

        every { mockViewModel.state } returns mutableStateOf(ShipListState(ships = ships))

        composeTestRule.setContent {
            ShipListScreen(navController = rememberNavController(), viewModel = mockViewModel)
        }

        composeTestRule.onNodeWithText("Real Name: Titanic").assertExists()
    }

    @Test
    fun shipListScreen_displaysErrorMessage_whenErrorOccurs() {
        every { mockViewModel.state } returns mutableStateOf(ShipListState(error = "Error loading ships"))

        composeTestRule.setContent {
            ShipListScreen(navController = rememberNavController(), viewModel = mockViewModel)
        }

        composeTestRule.onNodeWithText("Error loading ships").assertExists()
    }
}
