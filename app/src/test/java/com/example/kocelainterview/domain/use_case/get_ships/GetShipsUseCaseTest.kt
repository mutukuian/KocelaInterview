package com.example.kocelainterview.domain.use_case.get_ships

import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.data.remote.dto.ShipDto
import com.example.kocelainterview.data.remote.dto.toShip
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetShipsUseCaseTest {

    @Test
    fun `test invoke`() = runBlocking {
        // Mock repository
        val mockRepository = mock(ShipRepository::class.java)

        // Mock response from repository
        val mockShipDtos = listOf(
            ShipDto(
                
                active = true,
                image = "https://example.com/image1.jpg",
                ship_id = "ship_id_1",
                ship_name = "Ship 1",
                weight_kg = 1000,
                year_built = 2020
            )

        )
        `when`(mockRepository.getShips()).thenReturn(mockShipDtos)

        // Create an instance of GetShipsUseCase with the mocked repository
        val useCase = GetShipsUseCase(mockRepository)

        // Invoke the use case within a coroutine
        val result = useCase().toList()


        // Verify loading state
        assert(result[0] is Resource.Loading)

        // Verify success state
        val expectedSuccess = Resource.Success(mockShipDtos.map { it.toShip() })
        assert(result[1] is Resource.Success)
        assertEquals(expectedSuccess.data, (result[1] as Resource.Success).data)


    }
}
