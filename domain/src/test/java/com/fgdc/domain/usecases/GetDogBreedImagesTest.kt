package com.fgdc.domain.usecases

import com.fgdc.core.testcore.BaseTest
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.dog.DogBreedImagesDomain
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
class GetDogBreedImagesTest : BaseTest() {

    @MockK
    lateinit var repository: DogRepository

    private lateinit var getDogBreedsImages: GetDogBreedImages

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getDogBreedsImages = GetDogBreedImages(repository)
    }

    @Test
    fun `run should get success`() = runBlocking {
        val id = 0
        val flow = mockk<Flow<State<DogBreedImagesDomain>>>()
        every { repository.getDogBreedImages(id) } returns flow

        getDogBreedsImages.run(GetDogBreedImages.Params(id))

        verify(atLeast = 1) { repository.getDogBreedImages(id) }
        assertTrue(repository.getDogBreedImages(id) == flow)
    }
}
