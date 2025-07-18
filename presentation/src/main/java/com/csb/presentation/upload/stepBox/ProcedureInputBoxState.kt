package com.csb.presentation.upload.stepBox

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import java.util.UUID

data class ProcedureInputBoxState(
    val id: String = UUID.randomUUID().toString(),
    val procedureTitle: MutableState<String> = mutableStateOf(""),
    val procedureImage: MutableState<Uri> = mutableStateOf(Uri.EMPTY),
    val procedureContent: MutableState<String> = mutableStateOf("")
)