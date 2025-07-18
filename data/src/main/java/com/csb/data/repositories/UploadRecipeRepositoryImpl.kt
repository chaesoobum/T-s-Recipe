package com.csb.data.repositories

import com.csb.domain.repositories.UploadRecipeRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class UploadRecipeRepositoryImpl @Inject constructor(): UploadRecipeRepository {

    override suspend fun uploadRecipe() {
        delay(3000)
    }
}