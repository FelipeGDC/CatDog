package com.fgdc.data.repository.cat

import com.fgdc.core.utils.dispatcher.DefaultDispatcherProvider
import com.fgdc.core.utils.dispatcher.DispatcherProvider
import com.fgdc.data.datasource.cat.CatsRemoteDataSource
import com.fgdc.domain.repository.CatRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CatRepositoryImpl constructor(
    private val catRemoteDataSource: CatsRemoteDataSource,
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
) : CatRepository {
    override fun getCatBreeds() = flow {
        emit(catRemoteDataSource.getCatBreeds())
    }.flowOn(dispatcher.main)
}
