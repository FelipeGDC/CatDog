package com.fgdc.data.datasource.dog.api

import com.fgdc.data.datasource.dog.entity.DogBreedItemData
import com.fgdc.data.datasource.dog.entity.DogBreedListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {

    companion object {
        private const val DOG_BREEDS_ENDPOINT = "breeds"
        private const val DOG_IMAGES_ENDPOINT = "images/search"
    }

    @GET(DOG_BREEDS_ENDPOINT)
    suspend fun getDogBreeds(): Response<List<DogBreedItemData>>

    @GET(DOG_IMAGES_ENDPOINT)
    suspend fun getDogBreedImages(
        @Query("limit") limit: Int = 5,
        @Query("breed_id") id: Int
    ): Response<List<DogBreedListData>>
}
