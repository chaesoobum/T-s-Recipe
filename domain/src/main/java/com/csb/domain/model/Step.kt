package com.csb.domain.model

data class Step (
    val stepIndex:Int = 0,
    val stepName:String = "",
    val procedureList: List<Procedure> = emptyList()
)