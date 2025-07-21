package com.csb.presentation.upload.state

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.UUID

data class ProcedureInputBoxState(
    val id: String = UUID.randomUUID().toString(),
    val procedureTitle: MutableState<String> = mutableStateOf(""),
    val procedureImage: MutableState<Uri> = mutableStateOf(Uri.EMPTY),
    val procedureContent: MutableState<String> = mutableStateOf("")
)