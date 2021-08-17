package com.fgdc.catdogapp.ui.dogBreedDetail.models

import com.fgdc.domain.model.dog.DogBreedImagesDomain

data class DogBreedDetailView(
    val id: Int?,
    val bredFor: String?,
    val breedGroup: String?,
    val countryCode: String?,
    val description: String?,
    val history: String?,
    val lifeSpan: String?,
    val name: String?,
    val origin: String?,
    val temperament: String?,
    val images: List<String>?
)

fun DogBreedImagesDomain.toDogBreedItemView() = DogBreedDetailView(
    id,
    bredFor,
    breedGroup,
    countryCode,
    description,
    history,
    lifeSpan,
    name,
    origin,
    temperament,
    images
)
