package com.csb.presentation.upload.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.csb.presentation.upload.state.IngredientInputBoxState
import com.csb.presentation.upload.state.ProcedureInputBoxState
import com.csb.presentation.upload.stepBox.StepState

//레시피 업로드 베이스 폼
data class RecipeFormState(
    val title: MutableState<String> = mutableStateOf(""),
    val portion: MutableState<String> = mutableStateOf("4"),
    val cost: MutableState<String> = mutableStateOf(""),
    val time: MutableState<String> = mutableStateOf(""),
    val memo: MutableState<String> = mutableStateOf(""),

    val ingredientList: SnapshotStateList<IngredientInputBoxState> = mutableStateListOf(
        IngredientInputBoxState()
    ),
    val sourceList: SnapshotStateList<IngredientInputBoxState> = mutableStateListOf(
        IngredientInputBoxState()
    ),
    val stepList: SnapshotStateList<StepState> = mutableStateListOf(
        StepState(
            stepIndex = 0,
            procedureList = mutableStateListOf(ProcedureInputBoxState())
        )
    )
)