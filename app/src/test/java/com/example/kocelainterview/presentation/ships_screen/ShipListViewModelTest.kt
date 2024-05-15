package com.example.kocelainterview.presentation.ships_screen

import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.use_case.get_ships.GetShipsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ShipListViewModelTest {

    private lateinit var viewModel: ShipListViewModel
    private lateinit var getShipsUseCase: GetShipsUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getShipsUseCase = mockk(relaxed = true) // Use relaxed mode to handle final classes
        viewModel = ShipListViewModel(getShipsUseCase)
    }

    @Test
    fun testLoadingState(): Unit = runBlockingTest {
        // Given
        val loadingStateFlow = flow<Resource<List<Ship>>> { emit(Resource.Loading()) }
        every { getShipsUseCase() } returns loadingStateFlow

        // When
        viewModel.getShips()

        // Then
        assertEquals(ShipListState(isLoading = true), viewModel.state.value)
    }

    @Test
    fun testSuccessState(): Unit = runBlockingTest {
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
    fun testErrorState(): Unit = runBlockingTest {
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


