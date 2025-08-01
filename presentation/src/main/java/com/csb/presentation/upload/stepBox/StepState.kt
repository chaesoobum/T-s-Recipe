package com.csb.presentation.upload.stepBox

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.csb.presentation.upload.state.ProcedureInputBoxState


data class StepState(
    val stepIndex: Int = 0,
    val stepName:MutableState<String> = mutableStateOf(""),
    val procedureList: SnapshotStateList<ProcedureInputBoxState> = mutableStateListOf()
)

