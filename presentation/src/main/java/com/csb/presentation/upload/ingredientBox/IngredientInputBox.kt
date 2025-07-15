package com.csb.presentation.upload.ingredientBox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.SimpleOutlinedTextField

@Composable
fun IngredientInputBox(
    label: String,
    width: Dp,
    value: MutableState<String>,
    placeholder: String
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .width(width),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            label,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        SimpleOutlinedTextField(
            textFieldValue = value,
            paddingTop = 0.dp,
            paddingStart = 0.dp,
            paddingEnd = 0.dp,
            singleLine = true,
            placeHolder = placeholder
        )
    }
}


@Preview(
    showBackground = true
)
@Composable
fun PreviewIngredientInputBox() {
    val label = "재료 이름"
    val value = remember { mutableStateOf("") }
    IngredientInputBox(label = label, width = 200.dp, value = value, placeholder = "123")
}