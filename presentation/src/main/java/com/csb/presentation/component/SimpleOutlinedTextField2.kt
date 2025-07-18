package com.csb.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R

@Composable
fun SimpleOutlinedTextField2(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String = "",
    singleLine: Boolean = true,
    paddingTop: Dp = 0.dp,
    paddingStart: Dp = 0.dp,
    paddingEnd: Dp = 0.dp,
) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(top = paddingTop, start = paddingStart, end = paddingEnd)

    val pretendard = FontFamily(Font(R.font.pretendardvariable))
    val textColor = colorResource(id = R.color.textColor8c8c8c)
    val grayColor = colorResource(id = R.color.colorD9D9D9)

    val textFieldColors = TextFieldDefaults.colors(
        cursorColor = grayColor,
        focusedIndicatorColor = grayColor,
        unfocusedIndicatorColor = grayColor,
        focusedLabelColor = grayColor,
        focusedPlaceholderColor = grayColor,
        unfocusedPlaceholderColor = grayColor,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    )

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeHolder,
                fontSize = 14.sp,
                fontFamily = pretendard,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        singleLine = singleLine,
        colors = textFieldColors
    )
}
