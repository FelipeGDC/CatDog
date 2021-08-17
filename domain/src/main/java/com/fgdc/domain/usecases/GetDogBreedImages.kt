package com.fgdc.domain.usecases

import com.fgdc.core.fundaments.base.BaseUseCase
import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.dog.DogBreedImagesDomain
import com.fgdc.domain.repository.DogRepository
import javax.inject.Inject

class GetDogBreedImages @Inject constructor(private val dogRepository: DogRepository) :
    BaseUseCase<State<DogBreedImagesDomain>, GetDogBreedImages.Params>() {
    override fun run(params: Params?) = dogRepository.getDogBreedImages(params!!.breedId)

    class Params(var breedId: Int)
}
