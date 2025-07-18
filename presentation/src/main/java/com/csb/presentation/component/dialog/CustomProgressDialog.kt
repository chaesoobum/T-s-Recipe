package com.csb.presentation.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.csb.presentation.R


@Composable
fun CustomProgressDialog(
    isShowing: Boolean,
    onDismissRequest: () -> Unit = {},
    uploadProgress: Int? = null,
) {
    if (isShowing) {
        Dialog(onDismissRequest = onDismissRequest) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                // 세로로 배치 (로딩 → 퍼센트 텍스트)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(60.dp),
                        color = colorResource(id = R.color.textColor262626),
                        strokeWidth = 3.dp
                    )

                    // 진행률 텍스트가 있을 경우에만 표시
                    if (uploadProgress != null) {
                        Text(
                            text = "업로드 진행률: ${uploadProgress}%",
                            fontFamily = FontFamily(
                                Font(R.font.roboto_regular)
                            ),
                            fontSize = 15.sp,
                            color = colorResource(id = R.color.textColor262626)
                        )
                        LinearProgressIndicator(
                            progress = { uploadProgress / 100f },
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(id = R.color.textColor262626),
                            trackColor = Color.LightGray,
                            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                        )
                    }
                }
            }
        }
    }
}
