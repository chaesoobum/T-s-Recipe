package com.csb.presentation.component.colorBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.csb.presentation.R

@Composable
fun ColorBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(color = colorResource(id = R.color.colorE9E9E9))
    )
}