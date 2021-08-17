package com.fgdc.catdogapp.di

import com.fgdc.catdogapp.BuildConfig
import com.fgdc.core.utils.helpers.NetworkHandler
import com.fgdc.data.datasource.dog.DogsRemoteDataSource
import com.fgdc.data.datasource.dog.DogsRemoteDataSourceImpl
import com.fgdc.data.datasource.dog.api.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .followRedirects(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
        .client(okHttpClient)
        .baseUrl(BuildConfig.DOG_API_BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideDogRemoteDataSource(retrofit: Retrofit, networkHandler: NetworkHandler): DogsRemoteDataSource =
        DogsRemoteDataSourceImpl(retrofit.create(DogApi::class.java), networkHandler)
}
