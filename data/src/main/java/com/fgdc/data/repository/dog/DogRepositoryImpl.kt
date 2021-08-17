package com.fgdc.data.repository.dog

import com.fgdc.data.datasource.dog.DogsRemoteDataSource
import com.fgdc.domain.repository.DogRepository
import com.fgdc.core.utils.dispatcher.DefaultDispatcherProvider
import com.fgdc.core.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DogRepositoryImpl constructor(
    private val dogsRemoteDataSource: DogsRemoteDataSource,
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
) : DogRepository {
    override fun getDogBreeds() = flow {
        emit(dogsRemoteDataSource.getDogBreeds())
    }.flowOn(dispatcher.main)

    override fun getDogBreedImages(breedId: Int) = flow {
        emit(dogsRemoteDataSource.getDogBreedImages(breedId))
    }.flowOn(dispatcher.main)
}
