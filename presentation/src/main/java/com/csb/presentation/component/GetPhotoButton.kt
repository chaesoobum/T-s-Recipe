package com.csb.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csb.presentation.R

@Composable
fun GetPhotoButton(
    onClick:() -> Unit = {},
){
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .aspectRatio(1f) // 정사각형
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.colorD7D7D7),
                shape = RoundedCornerShape(5.dp)
            )
            .clickable{
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = "사진 추가",
            tint = colorResource(id = R.color.colorD7D7D7),
            modifier = Modifier
                .fillMaxSize(0.45f)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPhotoPlaceholder() {
    GetPhotoButton()
}