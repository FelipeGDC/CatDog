package com.fgdc.data.repository

import com.fgdc.core.testcore.BaseTest
import com.fgdc.core.utils.functional.State
import com.fgdc.data.datasource.dog.DogsRemoteDataSource
import com.fgdc.data.repository.dog.DogRepositoryImpl
import com.fgdc.domain.model.dog.DogBreedImagesDomain
import com.fgdc.domain.model.dog.DogBreedItemDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DogRepositoryImplTest : BaseTest() {

    @MockK
    lateinit var dogRemoteDataSource: DogsRemoteDataSource

    private lateinit var repository: DogRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository =
            DogRepositoryImpl(dogRemoteDataSource, coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun `getDogBreeds should get dog breeds from remote on success`(): Unit =
        runBlocking {
            val dogBreed: DogBreedItemDomain = mockk()
            val listDogBreeds: List<DogBreedItemDomain> = listOf(dogBreed, dogBreed)
            val successResponse = State.Success(listDogBreeds)

            coEvery {
                dogRemoteDataSource.getDogBreeds()
            } returns successResponse

            val flow = repository.getDogBreeds()

            flow.collect { result ->
                result.`should be instance of`<State.Success<List<DogBreedItemDomain>>>()
                when (result) {
                    is State.Success<List<DogBreedItemDomain>> -> {
                        result.data shouldBeEqualTo listDogBreeds
                    }
                }
            }
        }

    @Test
    fun `getDogBreedImages should get images from specific breed from remote on success`(): Unit =
        runBlocking {
            val id = 0
            val dogBreed: DogBreedImagesDomain = mockk()
            val successResponse = State.Success(dogBreed)

            coEvery {
                dogRemoteDataSource.getDogBreedImages(id)
            } returns successResponse

            val flow = repository.getDogBreedImages(id)

            flow.collect { result ->
                result.`should be instance of`<State.Success<DogBreedImagesDomain>>()
                when (result) {
                    is State.Success<DogBreedImagesDomain> -> {
                        result.data shouldBeEqualTo successResponse.data
                    }
                }
            }
        }
}
