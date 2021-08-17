package com.fgdc.domain.repository

import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.dog.DogBreedImagesDomain
import com.fgdc.domain.model.dog.DogBreedItemDomain
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    fun getDogBreeds(): Flow<State<List<DogBreedItemDomain>>>
    fun getDogBreedImages(breedId: Int): Flow<State<DogBreedImagesDomain>>
}
