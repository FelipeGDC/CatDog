package com.fgdc.data.datasource.dog.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogBreedListData(
    @Json(name = "breeds")
    var breeds: List<DogBreedItemData>?,
    @Json(name = "height")
    var height: Int?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "url")
    var url: String?,
    @Json(name = "width")
    var width: Int?
)

@JsonClass(generateAdapter = true)
data class DogBreedItemData(
    @Json(name = "bred_for")
    val bredFor: String?,
    @Json(name = "breed_group")
    val breedGroup: String?,
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "height")
    val height: HeightData?,
    @Json(name = "history")
    val history: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: ImageData?,
    @Json(name = "life_span")
    val lifeSpan: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "origin")
    val origin: String?,
    @Json(name = "reference_image_id")
    val referenceImageId: String?,
    @Json(name = "temperament")
    val temperament: String?,
    @Json(name = "weight")
    val weight: WeightData?
)

@JsonClass(generateAdapter = true)
data class HeightData(
    @Json(name = "imperial")
    val imperial: String?,
    @Json(name = "metric")
    val metric: String?
)

@JsonClass(generateAdapter = true)
data class WeightData(
    @Json(name = "imperial")
    val imperial: String?,
    @Json(name = "metric")
    val metric: String?
)

@JsonClass(generateAdapter = true)
data class ImageData(
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "width")
    val width: Int?
)
