package com.csb.data.repositories

import com.csb.domain.model.RecipeScreen
import com.csb.domain.repositories.RecipeRepository
import kotlinx.coroutines.delay
import javax.inject.Inject


class RecipeRepositoryImpl@Inject constructor(): RecipeRepository {
    override suspend fun fetchRecipeScreen(): RecipeScreen {

        //null이 있으면 "" 빈값으로 보내주자

        val recipeScreenDataClass = RecipeScreen(
            "",
            "브로콜리 두부 무침",
            "3",
            "4,000",
            "15",
            "홍길동"
        )

        delay(2000)

        return recipeScreenDataClass
    }
}