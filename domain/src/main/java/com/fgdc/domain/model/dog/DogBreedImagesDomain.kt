package com.fgdc.domain.model.dog

data class DogBreedImagesDomain(
    val id: Int?,
    val bredFor: String?,
    val breedGroup: String?,
    val countryCode: String?,
    val description: String?,
    val height: HeightDomain?,
    val history: String?,
    val lifeSpan: String?,
    val name: String?,
    val origin: String?,
    val temperament: String?,
    val weight: WeightDomain?,
    val images: List<String>?
)
