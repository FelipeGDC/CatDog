package com.fgdc.catdogapp.ui.dogBreedsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgdc.catdogapp.ui.dogBreedsList.models.DogBreedItemView
import com.fgdc.catdogapp.ui.dogBreedsList.models.toDogBreedItemView
import com.fgdc.core.fundaments.base.BaseUseCase
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.usecases.GetDogBreeds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedsListViewModel @Inject constructor(private val getDogBreeds: GetDogBreeds) :
    ViewModel() {

    private val defaultBreed = "All"
    private var dogBreedsUnfiltered = listOf<DogBreedItemView>()

    private val _dogBreeds = MutableStateFlow<State<List<DogBreedItemView>>?>(null)
    val dogBreeds: StateFlow<State<List<DogBreedItemView>>?> = _dogBreeds

    private val _dogBreedsNames = MutableStateFlow(emptyList<String>())
    val dogBreedsNames: StateFlow<List<String>> = _dogBreedsNames

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun getDogBreeds() {
        viewModelScope.launch {
            getDogBreeds(BaseUseCase.None())
                .onStart { _loading.value = true }
                .onEach { _loading.value = false }
                .catch { }
                .collect { result ->
                    when (result) {
                        is State.Success -> {
                            val breeds = mutableListOf(defaultBreed)
                            result.data.forEach {
                                breeds.add(it.name ?: "")
                            }
                            val dogBreeds = result.data.map { it.toDogBreedItemView() }
                            dogBreedsUnfiltered = dogBreeds
                            _dogBreeds.value = State.Success(dogBreeds)
                            _dogBreedsNames.value = breeds
                        }
                        is State.BadRequest -> _dogBreeds.value = result
                        is State.Error -> _dogBreeds.value = result
                        is State.ErrorNoConnection -> _dogBreeds.value = result
                    }
                }
        }
    }

    fun filterBreeds(selectedItem: String?) {
        if (selectedItem.equals(defaultBreed)) {
            _dogBreeds.value = State.Success(dogBreedsUnfiltered)
        } else {
            val filteredList = dogBreedsUnfiltered.filter { dog ->
                selectedItem!! == dog.name
            }
            if (filteredList.isNotEmpty()) {
                _dogBreeds.value = State.Success(filteredList)
            } else {
                _dogBreeds.value = State.Success(filteredList)
            }
        }
    }
}
