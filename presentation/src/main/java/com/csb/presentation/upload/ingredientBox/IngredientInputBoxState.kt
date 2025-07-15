package com.csb.presentation.upload.ingredientBox

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.UUID

data class IngredientInputBoxState(
    val id: String = UUID.randomUUID().toString(),
    val ingredientName: MutableState<String> = mutableStateOf(""),
    val ingredientTbsp: MutableState<String> = mutableStateOf(""),
    val ingredientQty: MutableState<String> = mutableStateOf(""),
    val ingredientCup: MutableState<String> = mutableStateOf(""),
    val ingredient_g: MutableState<String> = mutableStateOf(""),
    val ingredient_ml: MutableState<String> = mutableStateOf(""),
    val ingredient_oz: MutableState<String> = mutableStateOf(""),
)