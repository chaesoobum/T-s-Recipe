package com.csb.domain.model

data class RecipeScreen(
    val imageUrl: String = "",
    val title: String = "",
    val portion: String = "",
    val price: String = "",
    val time: String = "",
    val name: String = "",
    val memo: String = "",
    val ingredientList:List<Ingredient?> = emptyList(),
    val sourceList:List<Ingredient?> = emptyList(),
    val stepList:List<Step?> = emptyList()
)