package com.fgdc.data.datasource.dog

import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.dog.DogBreedImagesDomain
import com.fgdc.domain.model.dog.DogBreedItemDomain

interface DogsRemoteDataSource {
    suspend fun getDogBreeds(): State<List<DogBreedItemDomain>>
    suspend fun getDogBreedImages(breedId: Int): State<DogBreedImagesDomain>
}
