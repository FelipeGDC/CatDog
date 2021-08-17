package com.fgdc.data.datasource

import com.fgdc.core.testcore.BaseTest
import com.fgdc.core.utils.functional.State
import com.fgdc.core.utils.helpers.NetworkHandler
import com.fgdc.data.datasource.dog.DogsRemoteDataSourceImpl
import com.fgdc.data.datasource.dog.api.DogApi
import com.fgdc.data.datasource.dog.entity.DogBreedItemData
import com.fgdc.domain.model.dog.DogBreedItemDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class DogsRemoteDataSourceImplTest : BaseTest() {

    @MockK(relaxed = true)
    lateinit var dogApi: DogApi

    @MockK
    lateinit var networkHandler: NetworkHandler

    private lateinit var dogRemoteDataSource: DogsRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogRemoteDataSource =
            DogsRemoteDataSourceImpl(dogApi, networkHandler)
    }

    @Test
    fun `getDogBreeds should get dog breeds from dataSource on success`(): Unit =
        runBlocking {

            val listDogBreeds = mockk<List<DogBreedItemData>>(relaxed = true)

            coEvery {
                networkHandler.isInternetAvailable()
            } returns true

            coEvery { dogApi.getDogBreeds() } returns Response.success(listDogBreeds)

            val dataSourceState = dogRemoteDataSource.getDogBreeds()

            dataSourceState.`should be instance of`<State.Success<List<DogBreedItemDomain>>>()
            when (dataSourceState) {
                is State.Success<List<DogBreedItemDomain>> -> {
                    dataSourceState.data shouldBeEqualTo listDogBreeds
                }
            }
        }
}
