package com.csb.koreamoviedb_mvvm.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.csb.presentation.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val pretendard = FontFamily(Font(R.font.pretendard))

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = pretendard,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = pretendard,
        fontSize = 20.sp
    )
)