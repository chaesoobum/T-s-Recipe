package com.csb.domain.repositories

import com.csb.domain.model.RecipeScreen

interface RecipeRepository {
    suspend fun fetchRecipeScreen(): RecipeScreen
}