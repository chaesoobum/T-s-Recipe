package com.csb.presentation.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.csb.presentation.R

@Composable
fun ActiveTopBar(
    paddingTop: Dp,
    correctedAlpha: Float,
    routeScreenNavController: NavHostController
) {
//스크롤로 동적으로 투명도 조절
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp + paddingTop)
                .background(MaterialTheme.colorScheme.background.copy(alpha = correctedAlpha))
                .align(Alignment.TopStart)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp + paddingTop)
                .height(1.dp)
                .background(color = colorResource(id = R.color.textColor262626).copy(alpha = correctedAlpha))
                .align(Alignment.TopStart)
        )
        IconButton(
            onClick = {
                routeScreenNavController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = paddingTop)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "뒤로가기",
                tint = colorResource(id = R.color.textColor262626)
            )
        }
        IconButton(
            onClick = {
                //routeScreenNavController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = paddingTop)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_three_dots),
                contentDescription = "편집/삭제",
                tint = colorResource(id = R.color.textColor262626)
            )
        }
        //스크롤로 동적으로 투명도 조절
    }
}