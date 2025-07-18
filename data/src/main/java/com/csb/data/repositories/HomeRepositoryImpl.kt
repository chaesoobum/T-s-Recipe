package com.csb.data.repositories

import com.csb.domain.model.HomeRecipeItem
import com.csb.domain.repositories.HomeRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override suspend fun fetchHomeScreen(): List<HomeRecipeItem> {
        val recipeSharedDataClass1 = HomeRecipeItem(
            "https://raw.githubusercontent.com/realbeginnerr/tsrecipe/main/assets/img/r07_broccoliseasonedtofu.jpg",
            "브로콜리 두부 무침",
            "3",
            "4,000",
            "15",
            "홍길동"
        )
        val recipeSharedDataClass2 = HomeRecipeItem(
            "https://raw.githubusercontent.com/realbeginnerr/tsrecipe/main/assets/img/r06_chickenteriyaki.jpg",
            "닭다리살 데리야끼",
            "3",
            "4,000",
            "15",
            "홍길동"
        )
        val recipeSharedDataClass3 = HomeRecipeItem(
            "https://raw.githubusercontent.com/realbeginnerr/tsrecipe/main/assets/img/r04_jeyukbokkeum.jpg",
            "제육볶음",
            "3",
            "4,000",
            "15",
            "홍길동"
        )

        delay(1000)

        val result = listOf(
            recipeSharedDataClass1,
            recipeSharedDataClass2,
            recipeSharedDataClass3,
            recipeSharedDataClass1,
            recipeSharedDataClass2,
            recipeSharedDataClass3,
            recipeSharedDataClass1,
            recipeSharedDataClass2,
            recipeSharedDataClass3
        )

        return result
    }
}