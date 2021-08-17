package com.fgdc.domain.model.dog

data class DogBreedItemDomain(
    val bredFor: String?,
    val breedGroup: String?,
    val countryCode: String?,
    val description: String?,
    val height: HeightDomain?,
    val history: String?,
    val id: Int,
    val image: ImageDomain?,
    val lifeSpan: String?,
    val name: String?,
    val origin: String?,
    val referenceImageId: String?,
    val temperament: String?,
    val weight: WeightDomain?
)

data class HeightDomain(
    val imperial: String?,
    val metric: String?
)

data class ImageDomain(
    val height: Int?,
    val id: String?,
    val url: String?,
    val width: Int?
)

data class WeightDomain(
    val imperial: String?,
    val metric: String?
)
