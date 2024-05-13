package com.example.kocelainterview.presentation.ships_screen


import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.use_case.get_ships.GetShipsUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ShipListViewModelTest {

    private lateinit var viewModel: ShipListViewModel
    private lateinit var getShipsUseCase: GetShipsUseCase

    @Before
    fun setup() {
        getShipsUseCase = mock()
        viewModel = ShipListViewModel(getShipsUseCase)
    }

    @Test
    fun testLoadingState() = runBlockingTest {
        // Given
        val loadingStateFlow = flow<Resource<List<Ship>>> { emit(Resource.Loading()) }
        whenever(getShipsUseCase()).thenReturn(loadingStateFlow)

        // When
        viewModel.getShips()

        // Then
        assertEquals(ShipListState(isLoading = true), viewModel.state.value)
    }

    @Test
    fun testSuccessState() = runBlockingTest {
        // Given
        val mockShips = listOf(Ship(
            true, "image1.jpg", "ship_id_1", "Ship 1", 1000, 2020
        ))
        val successStateFlow = flow<Resource<List<Ship>>> { emit(Resource.Success(mockShips)) }
        whenever(getShipsUseCase()).thenReturn(successStateFlow)

        // When
        viewModel.getShips()

        // Then
        assertEquals(ShipListState(ships = mockShips), viewModel.state.value)
    }

    @Test
    fun testErrorState() = runBlockingTest {
        // Given
        val errorMessage = "An error occurred"
        val errorStateFlow = flow<Resource<List<Ship>>> { emit(Resource.Error(errorMessage)) }
        whenever(getShipsUseCase()).thenReturn(errorStateFlow)

        // When
        viewModel.getShips()

        // Then
        assertEquals(ShipListState(error = errorMessage), viewModel.state.value)
    }
}


