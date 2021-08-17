package com.fgdc.domain.usecases

import com.fgdc.core.fundaments.base.BaseUseCase
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.dog.DogBreedItemDomain
import com.fgdc.domain.repository.DogRepository
import javax.inject.Inject

class GetDogBreeds @Inject constructor(private val dogRepository: DogRepository) :
    BaseUseCase<State<List<DogBreedItemDomain>>, BaseUseCase.None>() {
    override fun run(params: None?) = dogRepository.getDogBreeds()
}
