package com.fgdc.catdogapp.utils

import com.fgdc.domain.model.dog.DogBreedImagesDomain
import com.fgdc.domain.model.dog.DogBreedItemDomain
import com.fgdc.domain.model.dog.HeightDomain
import com.fgdc.domain.model.dog.ImageDomain
import com.fgdc.domain.model.dog.WeightDomain

val testDogBreed = DogBreedItemDomain(
    "Small rodent hunting, lapdog",
    "Toy",
    "ES",
    "Description",
    HeightDomain("", ""),
    "History",
    1,
    ImageDomain(0, "", "", 0),
    "10 - 12 years",
    "Chow Chow",
    "Spain",
    "12345",
    "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
    WeightDomain("", "")
)

val testDogBreeds = listOf(
    DogBreedItemDomain(
        "Small rodent hunting, lapdog",
        "Toy",
        "ES",
        "Description",
        HeightDomain("", ""),
        "History",
        1,
        ImageDomain(0, "", "", 0),
        "10 - 12 years",
        "Chow Chow",
        "Spain",
        "12345",
        "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        WeightDomain("", "")
    ),
    DogBreedItemDomain(
        "Small rodent hunting, lapdog",
        "Toy",
        "ES",
        "Description",
        HeightDomain("", ""),
        "History",
        1,
        ImageDomain(0, "", "", 0),
        "10 - 12 years",
        "Akita",
        "Spain",
        "12345",
        "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        WeightDomain("", "")
    ),
    DogBreedItemDomain(
        "Small rodent hunting, lapdog",
        "Toy",
        "ES",
        "Description",
        HeightDomain("", ""),
        "History",
        1,
        ImageDomain(0, "", "", 0),
        "10 - 12 years",
        "Pug",
        "Spain",
        "12345",
        "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        WeightDomain("", "")
    ),
    DogBreedItemDomain(
        "Small rodent hunting, lapdog",
        "Toy",
        "ES",
        "Description",
        HeightDomain("", ""),
        "History",
        1,
        ImageDomain(0, "", "", 0),
        "10 - 12 years",
        "Pitbull",
        "Spain",
        "12345",
        "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        WeightDomain("", "")
    )
)

val testDogBreedImages = DogBreedImagesDomain(
    1,
    "Small rodent hunting, lapdog",
    "Toy",
    "ES",
    "Description",
    HeightDomain("", ""),
    "History",
    "10 - 12 years",
    "Chow Chow",
    "Spain",
    "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
    WeightDomain("", ""),
    listOf("", "", "")
)
