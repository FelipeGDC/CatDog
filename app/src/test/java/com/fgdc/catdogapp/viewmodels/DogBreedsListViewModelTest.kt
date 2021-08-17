package com.fgdc.catdogapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.fgdc.catdogapp.ui.dogBreedsList.DogBreedsListViewModel
import com.fgdc.catdogapp.ui.dogBreedsList.models.toDogBreedItemView
import com.fgdc.catdogapp.utils.testDogBreeds
import com.fgdc.core.testcore.BaseTest
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.usecases.GetDogBreeds
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
class DogBreedsListViewModelTest : BaseTest() {

    private lateinit var viewModel: DogBreedsListViewModel

    @MockK
    lateinit var getDogBreedsUseCase: GetDogBreeds

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DogBreedsListViewModel(getDogBreedsUseCase)
    }

    @Test
    fun testGetDogBreeds() = runBlocking {
        val stateSuccess = State.Success(testDogBreeds)
        val flowSuccess = MutableStateFlow(stateSuccess)

        coEvery { getDogBreedsUseCase.invoke(any()) } returns flowSuccess

        viewModel.getDogBreeds()

        viewModel.dogBreeds.test {
            val item = awaitItem()
            item.shouldHaveTheSameClassAs(stateSuccess)
            when (item) {
                is State.Success -> {
                    item.data shouldBeEqualTo stateSuccess.data.map { it.toDogBreedItemView() }
                }
            }
        }
    }
}
