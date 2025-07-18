package com.csb.presentation.recipe

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R

@Composable
fun PortionControl(
    viewModel: RecipeViewModel,
) {
    Text(
        text = stringResource(id = R.string.ingredient),
        color = colorResource(id = R.color.textColor262626),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
        modifier = Modifier
            .padding(top = 10.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {

            },
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_minus),
                contentDescription = "n인분 줄이기",
                tint = Color.Unspecified
            )
        }
        Text(
            text = "${viewModel.recipeScreenData.value?.portion ?:stringResource(id = R.string.loadFailed)} ${stringResource(id = R.string.serving)}",
            color = colorResource(id = R.color.textColor262626),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.pretendardvariable)),
        )
        IconButton(
            onClick = {

            },
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_plus),
                contentDescription = "n인분 추가하기",
                tint = Color.Unspecified
            )
        }
    }

}