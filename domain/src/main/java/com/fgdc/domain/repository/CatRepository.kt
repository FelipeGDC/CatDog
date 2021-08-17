package com.fgdc.domain.repository

import com.fgdc.core.utils.functional.State
import com.fgdc.domain.model.cat.CatBreedItemDomain
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCatBreeds(): Flow<State<List<CatBreedItemDomain>>>
}
