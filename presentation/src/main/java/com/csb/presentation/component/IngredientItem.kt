package com.csb.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csb.domain.model.Ingredient
import com.csb.presentation.R

@Composable
fun IngredientItem(
    ingredient: Ingredient?,
    backGroundColor: Color
) {
    val textColor = colorResource(id = R.color.textColor262626)
    val font = FontFamily(Font(R.font.pretendardvariable))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = backGroundColor)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = ingredient?.ingredientName ?: stringResource(id = R.string.loadFailed),
            color = textColor,
            fontFamily = font,
        )

        // 측정 단위 리스트 구성
        val units = listOf(
            ingredient?.ingredientTbsp to stringResource(id = R.string.Tbsp),
            ingredient?.ingredientTsp to stringResource(id = R.string.tsp),
            ingredient?.ingredientQty to stringResource(id = R.string.qty),
            ingredient?.ingredientCup to stringResource(id = R.string.cup),
            ingredient?.ingredient_g to stringResource(id = R.string.g),
            ingredient?.ingredient_ml to stringResource(id = R.string.ml),
            ingredient?.ingredient_oz to stringResource(id = R.string.oz),
        )

        units.forEach { (value, unitLabel) ->
            if (value != null && value != 0.0) {
                Text(
                    modifier = Modifier.padding(start = 25.dp),
                    text = "$value $unitLabel",
                    color = textColor,
                    fontFamily = font,
                )
            }
        }
    }
    HorizontalDivider(color = colorResource(id = R.color.colorD7D7D7))
}



@Composable
@Preview(showBackground = true)
fun PreviewIngredientItem() {
    IngredientItem(
        ingredient = null,
        backGroundColor = colorResource(id = R.color.colorF5F5F5)
    )
}