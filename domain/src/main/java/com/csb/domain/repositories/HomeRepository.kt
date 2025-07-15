package com.csb.domain.repositories

import com.csb.domain.model.HomeRecipeItem

interface HomeRepository {
    suspend fun fetchHomeScreen(): List<HomeRecipeItem>
}