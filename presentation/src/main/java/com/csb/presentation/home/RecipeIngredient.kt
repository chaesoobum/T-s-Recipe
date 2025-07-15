package com.csb.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.IngredientItem

@Composable
fun RecipeIngredient(){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        text = stringResource(id = R.string.basicIngredients),
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendard)),
        color = colorResource(R.color.textColor262626),
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )
    Spacer(
        modifier = Modifier.height(10.dp)
    )
    HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(2.dp)
            .background(color = colorResource(id = R.color.colorD9D9D9))
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .wrapContentHeight()
    ) {
        val dummyItems = listOf("재료 1", "재료 2", "재료 3")

        Column {
            dummyItems.forEachIndexed { index, item ->
                if ((index + 1) % 2 == 1) {
                    IngredientItem(
                        ingredientName = "브로콜리",
                        ingredientUnitList = listOf("2", "100g", "90oz"),
                        backGroundColor = MaterialTheme.colorScheme.background
                    )
                } else {
                    IngredientItem(
                        ingredientName = "브로콜리",
                        ingredientUnitList = listOf("2", "100g", "90oz"),
                        backGroundColor = colorResource(id = R.color.colorF5F5F5)
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}