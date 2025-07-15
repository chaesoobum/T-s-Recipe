package com.csb.presentation.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton

@Composable
fun CustomDialog(
    icon: ImageVector = Icons.Default.Info,
    message: String,
    onConfirm: () -> Unit,
    onConfirmText: String = stringResource(id = R.string.confirm),
    onDismiss: () -> Unit,
    onDismissText: String = stringResource(id = R.string.cancel),
    showDialog: Boolean,
    onDismissRequest: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(dismissOnClickOutside = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = colorResource(id = R.color.textColor262626)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = message,
                    fontFamily = FontFamily(Font(R.font.pretendardvariable)),
                    color = colorResource(id = R.color.textColor262626)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomBoxOutlineButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp),
                        text = onDismissText,
                        onClick = {
                            onDismiss()
                            onDismissRequest()
                        },
                        buttonColor = Color.Transparent,
                        borderColor = colorResource(id = R.color.textColor262626),
                        textColor = colorResource(id = R.color.textColor262626),
                    )
                    CustomBoxOutlineButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp),
                        text = onConfirmText,
                        onClick = {
                            onConfirm()
                            onDismissRequest()
                        },
                        buttonColor = Color.Transparent,
                        borderColor = colorResource(id = R.color.textColor262626),
                        textColor = colorResource(id = R.color.textColor262626),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomDialog() {
    var showDialog by remember { mutableStateOf(true) }

    CustomDialog(
        icon = Icons.Default.Info,
        message = "진행하시겠습니까?",
        onConfirm = { /* 확인 로직 */ },
        onDismiss = { /* 취소 로직 */ },
        showDialog = showDialog,
        onDismissRequest = { showDialog = false }
    )
}
