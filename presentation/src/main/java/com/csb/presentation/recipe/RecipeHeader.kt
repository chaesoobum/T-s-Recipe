package com.csb.presentation.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.SubComposeAsyncImage
import com.csb.presentation.component.colorBox.ColorBox
import com.csb.presentation.component.spacer.Spacer_Height10DP

@Composable
fun RecipeHeader(
    imageUrl: String?,
    title: String?,
    serving: String?,
    price: String?,
    time: String?,
    name: String?,
    memo:String?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(Color.Transparent)
    ) {
        if (imageUrl.isNullOrEmpty()) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.no_image),
                contentDescription = "기본 레시피 이미지",
                contentScale = ContentScale.Crop
            )
        } else {
            SubComposeAsyncImage(
                imgUrl = imageUrl,
                contentDescription = "레시피 이미지",
                contentScale = ContentScale.Crop,
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        //text = viewModel.title,
        text = title ?: stringResource(id = R.string.loadFailed),
        color = colorResource(id = R.color.textColor262626),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )
    Spacer_Height10DP()
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "${serving ?: stringResource(id = R.string.loadFailed)}${stringResource(id = R.string.serving)} · ${price ?: stringResource(id = R.string.loadFailed)}${stringResource(id = R.string.price)
        } · ${time ?: stringResource(id = R.string.loadFailed)}${stringResource(id = R.string.minute)}",
        color = colorResource(id = R.color.textColor8c8c8c),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )
    Spacer_Height10DP()
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        //text = viewModel.name,
        text =  name ?: stringResource(id = R.string.loadFailed),
        color = colorResource(id = R.color.textColor8c8c8c),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )
    Spacer_Height10DP()
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = memo ?: stringResource(id = R.string.loadFailed),
        color = colorResource(id = R.color.textColor8c8c8c),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
    )
    Spacer_Height10DP()
    ColorBox()

}