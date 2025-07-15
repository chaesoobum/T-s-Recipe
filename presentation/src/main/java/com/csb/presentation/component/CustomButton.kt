package com.csb.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csb.presentation.R


@Composable
fun CustomOutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    buttonColor: Color = colorResource(id = R.color.textColor262626),
    borderColor: Color = colorResource(id = R.color.textColor262626),
    textColor: Color = colorResource(id = R.color.textColor262626),
    icon: Painter? = null,
    isSameColor: Boolean = true
) {
    val shape = RoundedCornerShape(5.dp)

    OutlinedButton(
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        border = BorderStroke(1.dp, borderColor),
    ) {
        if (icon!=null){
            if (isSameColor == false){
                Icon(
                    painter = icon,
                    contentDescription = null,
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
            color = textColor,
            fontFamily = FontFamily(Font(R.font.pretendardvariable))
        )
    }
}
@Preview
@Composable
fun PreviewCustomOutlineButton(){
    CustomOutlineButton(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Sign up for free",
        onClick = { },
        buttonColor = Color.Transparent,
        borderColor = Color.Black,
        textColor = Color.Black,
        icon = painterResource(id = R.drawable.add_circle_24px)
    )
}
