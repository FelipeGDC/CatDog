package com.fgdc.catdogapp.ui.dogBreedsList.models

import com.fgdc.domain.model.dog.DogBreedItemDomain

data class DogBreedItemView(
    val id: Int,
    val bredFor: String?,
    val breedGroup: String?,
    val countryCode: String?,
    val description: String?,
    val history: String?,
    val image: String?,
    val lifeSpan: String?,
    val name: String?,
    val origin: String?,
    val temperament: String?
)

fun DogBreedItemDomain.toDogBreedItemView() = DogBreedItemView(
    id,
    bredFor,
    breedGroup,
    countryCode,
    description,
    history,
    image?.url,
    lifeSpan,
    name,
    origin,
    temperament
)
