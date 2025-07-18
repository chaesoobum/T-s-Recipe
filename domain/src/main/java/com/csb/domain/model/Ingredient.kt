package com.csb.domain.model

data class Ingredient(
    val ingredientName: String = "",
    val ingredientTbsp: Double = 0.0,
    val ingredientTsp: Double = 0.0,
    val ingredientQty: Double = 0.0,
    val ingredientCup: Double = 0.0,
    val ingredient_g: Double = 0.0,
    val ingredient_ml: Double = 0.0,
    val ingredient_oz: Double = 0.0,
)
