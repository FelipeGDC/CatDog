package com.fgdc.data.mappers

import com.fgdc.data.datasource.dog.entity.*
import com.fgdc.domain.model.dog.*

fun DogBreedItemData.toDogBreedItemDomain() =
    DogBreedItemDomain(
        bredFor,
        breedGroup,
        countryCode,
        description,
        height?.toDomain(),
        history,
        id,
        image?.toDomain(),
        lifeSpan,
        name,
        origin,
        referenceImageId,
        temperament,
        weight?.toDomain()
    )

fun HeightData.toDomain() = HeightDomain(imperial, metric)

fun WeightData.toDomain() = WeightDomain(imperial, metric)

fun ImageData.toDomain() = ImageDomain(height, id, url, width)

fun List<DogBreedListData>.toDogBreedImagesDomain(): DogBreedImagesDomain {
    val images: List<String> = this.map { it.url ?: "" }
    return this[0].breeds?.get(0).let { dog ->
        DogBreedImagesDomain(
            dog?.id,
            dog?.bredFor,
            dog?.breedGroup,
            dog?.countryCode,
            dog?.description,
            dog?.height?.toDomain(),
            dog?.history,
            dog?.lifeSpan,
            dog?.name,
            dog?.origin,
            dog?.temperament,
            dog?.weight?.toDomain(),
            images
        )
    }
}
