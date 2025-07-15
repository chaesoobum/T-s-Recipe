package com.csb.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R

@Composable
fun CustomBoxOutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    buttonColor: Color = Color.Transparent,
    borderColor: Color = colorResource(id = R.color.textColor262626),
    textColor: Color = colorResource(id = R.color.textColor262626),
    textFont:FontFamily = FontFamily(Font(R.font.pretendardvariable)),
    icon: Painter? = null,
    isSameColor: Boolean = true
) {
    val shape = RoundedCornerShape(5.dp)

    Box(
        modifier = modifier
            .border(BorderStroke(1.dp, borderColor), shape)
            .background(buttonColor, shape)
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                if (icon!=null){
                    if (isSameColor == false){
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }else{
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            tint = textColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Text(
                    text = text,
                    fontSize = 14.sp,
                    color = textColor,
                    fontFamily = textFont
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomBoxOutlineButton() {
    CustomBoxOutlineButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Sign up for free",
        onClick = { },
        buttonColor = Color.Transparent,
        borderColor = Color.Black,
        textColor = Color.Black,
    )
}