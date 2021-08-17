package com.fgdc.catdogapp.ui.dogBreedDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgdc.catdogapp.ui.dogBreedDetail.models.DogBreedDetailView
import com.fgdc.catdogapp.ui.dogBreedDetail.models.toDogBreedItemView
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.usecases.GetDogBreedImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedDetailViewModel @Inject constructor(private val getDogBreedImages: GetDogBreedImages) :
    ViewModel() {

    private val _dogBreedDetail = MutableStateFlow<State<DogBreedDetailView>?>(null)
    val dogBreedDetail: StateFlow<State<DogBreedDetailView>?> = _dogBreedDetail

    fun getDogBreedImages(breedId: Int) {
        viewModelScope.launch {
            getDogBreedImages(GetDogBreedImages.Params(breedId))
                .onStart { }
                .onEach { }
                .catch { }
                .collect { result ->
                    when (result) {
                        is State.Success -> {
                            _dogBreedDetail.value = State.Success(result.data.toDogBreedItemView())
                        }
                    }
                }
        }
    }
}
