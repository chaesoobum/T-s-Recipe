package com.csb.presentation.upload.ingredientBox


import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.Dp

data class IngredientField(
    val label: String,
    val width: Dp,
    val value: MutableState<String>,
    val placeholder: String
)
