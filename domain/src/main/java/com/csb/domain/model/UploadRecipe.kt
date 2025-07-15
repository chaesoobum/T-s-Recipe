package com.csb.domain.model

data class UploadRecipe(
    val imageUrl: String = "",
    val title: String = "",
    val portion: String = "",
    val price: String = "",
    val time: String = "",
    val name: String = "",

    //재료 리스트
    val ingredientList:List<Ingredient> = emptyList(),

    //소스 리스트
    val sourceList:List<Ingredient> = emptyList(),

    //스텝,과정
    val stepList :List<Step> = emptyList()
)
