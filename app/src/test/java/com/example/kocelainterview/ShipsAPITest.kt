package com.example.kocelainterview

import com.example.kocelainterview.data.remote.api_service.ShipsApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ShipsAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var shipsAPI: ShipsApi

    @Before
    fun setup() {
        //setup mockwebserver
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockRequestDispatcher()
        mockWebServer.start()

        //setup retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        shipsAPI = retrofit.create(ShipsApi::class.java)
    }

    @Test
    fun `fetchShips() returns a list of ships`() = runTest{
        val ships = shipsAPI.getShips()
        assert(ships.size == 3)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

}