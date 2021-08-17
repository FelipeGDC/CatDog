package com.fgdc.data.datasource.cat.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreedListData(
    @Json(name = "breeds")
    var breeds: List<CatBreedListDataItem>?,
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
data class CatBreedListDataItem(
    @Json(name = "adaptability")
    var adaptability: Int?,
    @Json(name = "affection_level")
    var affectionLevel: Int?,
    @Json(name = "alt_names")
    var altNames: String?,
    @Json(name = "bidability")
    var bidability: Int?,
    @Json(name = "cat_friendly")
    var catFriendly: Int?,
    @Json(name = "cfa_url")
    var cfaUrl: String?,
    @Json(name = "child_friendly")
    var childFriendly: Int?,
    @Json(name = "country_code")
    var countryCode: String?,
    @Json(name = "country_codes")
    var countryCodes: String?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "dog_friendly")
    var dogFriendly: Int?,
    @Json(name = "energy_level")
    var energyLevel: Int?,
    @Json(name = "experimental")
    var experimental: Int?,
    @Json(name = "grooming")
    var grooming: Int?,
    @Json(name = "hairless")
    var hairless: Int?,
    @Json(name = "health_issues")
    var healthIssues: Int?,
    @Json(name = "hypoallergenic")
    var hypoallergenic: Int?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "image")
    var image: Image?,
    @Json(name = "indoor")
    var indoor: Int?,
    @Json(name = "intelligence")
    var intelligence: Int?,
    @Json(name = "lap")
    var lap: Int?,
    @Json(name = "life_span")
    var lifeSpan: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "natural")
    var natural: Int?,
    @Json(name = "origin")
    var origin: String?,
    @Json(name = "rare")
    var rare: Int?,
    @Json(name = "reference_image_id")
    var referenceImageId: String?,
    @Json(name = "rex")
    var rex: Int?,
    @Json(name = "shedding_level")
    var sheddingLevel: Int?,
    @Json(name = "short_legs")
    var shortLegs: Int?,
    @Json(name = "social_needs")
    var socialNeeds: Int?,
    @Json(name = "stranger_friendly")
    var strangerFriendly: Int?,
    @Json(name = "suppressed_tail")
    var suppressedTail: Int?,
    @Json(name = "temperament")
    var temperament: String?,
    @Json(name = "vcahospitals_url")
    var vcahospitalsUrl: String?,
    @Json(name = "vetstreet_url")
    var vetstreetUrl: String?,
    @Json(name = "vocalisation")
    var vocalisation: Int?,
    @Json(name = "weight")
    var weight: Weight?,
    @Json(name = "wikipedia_url")
    var wikipediaUrl: String?
)

@JsonClass(generateAdapter = true)
data class Image(
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
data class Weight(
    @Json(name = "imperial")
    var imperial: String?,
    @Json(name = "metric")
    var metric: String?
)
