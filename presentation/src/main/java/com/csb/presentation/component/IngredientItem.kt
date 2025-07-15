package com.csb.presentation.component

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csb.presentation.R

@Composable
fun IngredientItem(
    ingredientName: String,
    ingredientUnitList: List<String>,
    backGroundColor:Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = backGroundColor)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp),
            text = ingredientName,
            color = colorResource(id = R.color.textColor262626),
            fontFamily = FontFamily(Font(R.font.pretendardvariable)),
        )
        ingredientUnitList.forEach {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp),
                text = it,
                color = colorResource(id = R.color.textColor262626),
                fontFamily = FontFamily(Font(R.font.pretendardvariable)),
            )
        }
    }
    Divider(color = colorResource(id = R.color.colorD7D7D7))
}


@Composable
@Preview(showBackground = true)
fun PreviewIngredientItem() {
    IngredientItem(
        ingredientName = "브로콜리",
        ingredientUnitList = listOf("1"),
        backGroundColor = colorResource(id = R.color.white)
    )
}