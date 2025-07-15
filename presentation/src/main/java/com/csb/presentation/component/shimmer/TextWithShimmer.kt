package com.csb.presentation.component.shimmer

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.csb.presentation.R
import com.csb.presentation.util.Tools.rememberDefaultShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun TextWithShimmer(
    text: String,
    fontSize: androidx.compose.ui.unit.TextUnit,
    modifier: Modifier = Modifier
) {
    val shimmer = rememberDefaultShimmer()

    Text(
        text = text,
        fontSize = fontSize,
        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
        color = Color.Transparent,
        modifier = modifier
            .shimmer(shimmer)
            .background(Color.LightGray.copy(alpha = 0.1f))
    )
}