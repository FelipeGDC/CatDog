package com.fgdc.data.datasource.cat

import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.cat.CatBreedItemDomain

interface CatsRemoteDataSource {
    fun getCatBreeds(): State<List<CatBreedItemDomain>>
}
