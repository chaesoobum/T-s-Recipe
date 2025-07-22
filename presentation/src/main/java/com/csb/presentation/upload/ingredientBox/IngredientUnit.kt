package com.csb.presentation.upload.ingredientBox

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.csb.presentation.R

data class IngredientUnit(
    val ingredientUnitLabel:String = "",
    val ingredientUnitValue: MutableState<String> = mutableStateOf("")
)
