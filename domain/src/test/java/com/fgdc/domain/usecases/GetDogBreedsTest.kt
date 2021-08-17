package com.fgdc.domain.usecases

import com.fgdc.core.fundaments.base.BaseUseCase
import com.fgdc.core.testcore.BaseTest
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.dog.DogBreedItemDomain
import com.fgdc.domain.repository.DogRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDogBreedsTest : BaseTest() {

    @MockK
    lateinit var repository: DogRepository

    private lateinit var getDogBreeds: GetDogBreeds

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getDogBreeds = GetDogBreeds(repository)
    }

    @Test
    fun `run should get success`() = runBlocking {
        val flow = mockk<Flow<State<List<DogBreedItemDomain>>>>()
        every { repository.getDogBreeds() } returns flow

        getDogBreeds.run(BaseUseCase.None())

        verify(atLeast = 1) { repository.getDogBreeds() }
        assertTrue(repository.getDogBreeds() == flow)
    }
}
