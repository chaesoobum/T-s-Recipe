package com.csb.presentation.upload.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList


data class StepState(
    val stepIndex: Int = 0,
    val stepName:MutableState<String> = mutableStateOf(""),
    val procedureList: SnapshotStateList<ProcedureInputBoxState> = mutableStateListOf()
)

