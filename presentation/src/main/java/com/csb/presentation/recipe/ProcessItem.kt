package com.csb.presentation.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R

@Composable
fun ProcessItem(
    text: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                color = colorResource(id = R.color.textColor262626),
                fontFamily = FontFamily(Font(R.font.pretendardvariable)),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        }
        HorizontalDivider(
            color = colorResource(id = R.color.colorD7D7D7),
            thickness = 1.dp
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewProcessItem() {
    ProcessItem("냄비에 물, 찜기넣고 끓이기")
}