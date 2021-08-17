package com.fgdc.data.datasource.dog

import com.fgdc.core.utils.exception.ErrorHandler
import com.fgdc.core.utils.functional.State
import com.fgdc.core.utils.helpers.NetworkHandler
import com.fgdc.data.datasource.dog.api.DogApi
import com.fgdc.data.mappers.toDogBreedImagesDomain
import com.fgdc.data.mappers.toDogBreedItemDomain
import com.fgdc.domain.model.dog.DogBreedImagesDomain
import com.fgdc.domain.model.dog.DogBreedItemDomain

class DogsRemoteDataSourceImpl(
    private val dogApi: DogApi,
    private val networkHandler: NetworkHandler
) : DogsRemoteDataSource {
    override suspend fun getDogBreeds(): State<List<DogBreedItemDomain>> {
        if (!networkHandler.isInternetAvailable()) {
            return State.ErrorNoConnection(Throwable(ErrorHandler.NETWORK_ERROR_MESSAGE))
        }
        return dogApi.getDogBreeds().run {
            if (isSuccessful) {
                State.Success(body()?.map { it.toDogBreedItemDomain() } ?: emptyList())
            } else {
                State.BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
            }
        }
    }

    override suspend fun getDogBreedImages(breedId: Int): State<DogBreedImagesDomain> {
        if (!networkHandler.isInternetAvailable()) {
            return State.ErrorNoConnection(Throwable(ErrorHandler.NETWORK_ERROR_MESSAGE))
        }
        return dogApi.getDogBreedImages(id = breedId).run {
            if (isSuccessful) {
                body()?.let { body ->
                    State.Success(body.toDogBreedImagesDomain())
                } ?: State.BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
            } else {
                State.BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
            }
        }
    }
}
