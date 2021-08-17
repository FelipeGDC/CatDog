package com.fgdc.catdogapp.di

import com.fgdc.data.datasource.dog.DogsRemoteDataSource
import com.fgdc.data.repository.dog.DogRepositoryImpl
import com.fgdc.domain.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideDogRepository(
        dogsRemoteDataSource: DogsRemoteDataSource,
    ): DogRepository = DogRepositoryImpl(dogsRemoteDataSource)
}
