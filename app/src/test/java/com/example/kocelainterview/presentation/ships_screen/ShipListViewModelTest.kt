package com.example.kocelainterview.presentation.ships_screen

import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.use_case.get_ships.GetShipsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ShipListViewModelTest {

    private lateinit var viewModel: ShipListViewModel
    private lateinit var getShipsUseCase: GetShipsUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined) //Because we are using coroutines in our viewmodel
        getShipsUseCase = mockk(relaxed = true)
        viewModel = ShipListViewModel(getShipsUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadingState() = runTest {
        // Given
        val loadingStateFlow = flow<Resource<List<Ship>>> { emit(Resource.Loading()) }
        every { getShipsUseCase() } returns loadingStateFlow

        // When
        viewModel.getShips()

        // Then
        assertEquals(ShipListState(isLoading = true), viewModel.state.value)
    }

    @Test
    fun testSuccessState() = runTest {
        // Given
        val mockShips = listOf(Ship(
            true, "image1.jpg", "ship_id_1", "Ship 1", 1000, 2020
        ))
        val successStateFlow = flow<Resource<List<Ship>>> { emit(Resource.Success(mockShips)) }
        every { getShipsUseCase() } returns successStateFlow

        // When
        viewModel.getShips()

        // Then
        assertEquals(ShipListState(ships = mockShips), viewModel.state.value)
    }

    @Test
    fun testErrorState() = runTest {
        // Given
        val errorMessage = "An error occurred"
        val errorStateFlow = flow<Resource<List<Ship>>> { emit(Resource.Error(errorMessage)) }
        every { getShipsUseCase() } returns errorStateFlow

        // When
        viewModel.getShips()

        // Then
        assertEquals(ShipListState(error = errorMessage), viewModel.state.value)
    }
}