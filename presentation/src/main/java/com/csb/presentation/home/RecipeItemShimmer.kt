package com.csb.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.component.shimmer.TextWithShimmer
import com.csb.presentation.util.Tools.rememberDefaultShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun RecipeItemShimmer() {
    val shimmer = rememberDefaultShimmer()

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .shimmer(shimmer)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.LightGray.copy(alpha = 0.1f))
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            TextWithShimmer(
                text = "임시텍스트",
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextWithShimmer(
                text = "임시텍스트임시텍스트",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextWithShimmer(
                text = "홍길동",
                fontSize = 14.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}

@Composable
@Preview(showBackground = true)
fun previewRecipeItemShimmer() {
    RecipeItemShimmer()
}
