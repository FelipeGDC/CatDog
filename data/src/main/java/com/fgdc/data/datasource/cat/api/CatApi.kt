package com.fgdc.data.datasource.cat.api

import com.fgdc.data.datasource.cat.entity.CatBreedListData
import com.fgdc.data.datasource.cat.entity.CatBreedListDataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    companion object {
        private const val CAT_BREEDS_ENDPOINT = "breeds"
        private const val CAT_IMAGES_ENDPOINT = "images/search"
    }

    @GET(CAT_BREEDS_ENDPOINT)
    suspend fun getCatBreeds(): Response<List<CatBreedListDataItem>>

    @GET(CAT_IMAGES_ENDPOINT)
    suspend fun getCatBreedImages(
        @Query("limit") limit: Int = 5,
        @Query("breed_id") id: Int
    ): Response<List<CatBreedListData>>
}
