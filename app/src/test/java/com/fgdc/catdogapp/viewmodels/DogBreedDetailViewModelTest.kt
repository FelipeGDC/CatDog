package com.fgdc.catdogapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.fgdc.catdogapp.ui.dogBreedDetail.DogBreedDetailViewModel
import com.fgdc.catdogapp.ui.dogBreedDetail.models.toDogBreedItemView
import com.fgdc.catdogapp.utils.testDogBreedImages
import com.fgdc.core.testcore.BaseTest
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.usecases.GetDogBreedImages
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class DogBreedDetailViewModelTest : BaseTest() {

    private lateinit var viewModel: DogBreedDetailViewModel

    @MockK
    lateinit var getDoBreedsImages: GetDogBreedImages

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DogBreedDetailViewModel(getDoBreedsImages)
    }

    @Test
    fun testGetDogBreeds() = runBlocking {
        val id = 1
        val stateSuccess = State.Success(testDogBreedImages)
        val flowSuccess = MutableStateFlow(stateSuccess)

        coEvery { getDoBreedsImages.invoke(any()) } returns flowSuccess

        viewModel.getDogBreedImages(id)

        viewModel.dogBreedDetail.test {
            val item = awaitItem()
            item.shouldHaveTheSameClassAs(stateSuccess)
            when (item) {
                is State.Success -> {
                    item.data shouldBeEqualTo stateSuccess.data.toDogBreedItemView()
                }
            }
        }
    }
}
