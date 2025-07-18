package com.csb.presentation.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.csb.domain.model.Ingredient
import com.csb.presentation.R
import com.csb.presentation.component.IngredientItem
import com.csb.presentation.component.spacer.Spacer_Height10DP
import com.csb.presentation.component.spacer.Spacer_Height20DP

@Composable
fun RecipeIngredient(
    isSource: Boolean = false,
    processedIngredient: List<Ingredient?>
){
    //재료/소스 분기
    val text = if (isSource) stringResource(id = R.string.basicIngredients)else stringResource(id = R.string.source)
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        text = text,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendard)),
        color = colorResource(R.color.textColor262626),
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )
    Spacer_Height10DP()
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
        Column {
            processedIngredient.forEachIndexed { index, it->
                if ((index + 1) % 2 == 1) {
                    IngredientItem(
                        ingredient = it,
                        backGroundColor = MaterialTheme.colorScheme.background
                    )
                } else {
                    IngredientItem(
                        ingredient = it,
                        backGroundColor = colorResource(id = R.color.colorF5F5F5)
                    )
                }
            }
        }
    }
    Spacer_Height20DP()
}