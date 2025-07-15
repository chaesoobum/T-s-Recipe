package com.csb.presentation.upload.state

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState

data class ProcedureInputBoxState(
    val procedureTitle: MutableState<String> = mutableStateOf(""),
    val procedureImage: MutableState<Uri> = mutableStateOf(Uri.EMPTY),
    val procedureContent: MutableState<String> = mutableStateOf("")
)