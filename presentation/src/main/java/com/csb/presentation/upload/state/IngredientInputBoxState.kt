package com.csb.presentation.upload.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.csb.presentation.R
import java.util.UUID

data class IngredientInputBoxState(
    val id: String = UUID.randomUUID().toString(),
    val ingredientName: MutableState<String> = mutableStateOf(""),
    val ingredientUnitList : MutableMap<String, MutableState<String>> = mutableMapOf(
        "Tbsp" to mutableStateOf(""),
        "tsp" to mutableStateOf(""),
        "Qty" to mutableStateOf(""),
        "Cup" to mutableStateOf(""),
        "g" to mutableStateOf(""),
        "ml" to mutableStateOf(""),
        "oz" to mutableStateOf(""),
    )
)