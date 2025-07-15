package com.csb.presentation.home

import android.widget.Scroller
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.SubComposeAsyncImage

@Composable
fun RecipeHeader(
    imageUrl: String,
    title: String,
    serving: String,
    price: String,
    time: String,
    memo:String,
    rememberScrollState:ScrollState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(Color.Transparent)
    ) {
        SubComposeAsyncImage(
            //imgUrl = recipe.imageUrl,
            imgUrl = imageUrl,
            contentDescription = "레시피 이미지",
            contentScale = ContentScale.Crop,
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        //text = viewModel.title,
        text = "연근 조림",
        color = colorResource(id = R.color.textColor262626),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        //text = "${viewModel.serving}${stringResource(id = R.string.serving)} · ${viewModel.price}${stringResource(id = R.string.price)} · ${{viewModel.time}${stringResource(id = R.string.minute)}",
        text = "${3}${stringResource(id = R.string.serving)} · ${"4,000"}${
            stringResource(
                id = R.string.price
            )
        } · ${"15"}${stringResource(id = R.string.minute)}",
        color = colorResource(id = R.color.textColor8c8c8c),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )

    Spacer(modifier = Modifier.height(10.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        //text = viewModel.name,
        text = "홍길동",
        color = colorResource(id = R.color.textColor8c8c8c),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "기타 메모는 여기에.\n참고한 레시피 원본 출처 내용 적으면 됨",
        color = colorResource(id = R.color.textColor8c8c8c),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(color = colorResource(id = R.color.colorE9E9E9))
    )

}