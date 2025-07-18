package com.csb.domain.model

data class ProcessedRecipeScreen (
    val imageUrl: String = "",
    val title: String = "",
    val portion: String = "",
    val price: String = "",
    val time: String = "",
    val name: String = "",
    val memo: String = "",
    val ingredientList: Map<String, Map<String, Double>> = emptyMap(),
    val sourceList: Map<String, Map<String, Double>> = emptyMap(),
    val stepList: List<Step?> = emptyList()
)