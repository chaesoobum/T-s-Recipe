package com.csb.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(
    // 입력값에 대한 상태관리 변수
    textFieldValue: MutableState<String>,
    // hint
    label: String = "",
    // 포커스가 주여지고 입력된 내용이 없을 경우 보여줄 안내 문구
    placeHolder: String = "",
    // 입력 제한을 주기위한 정규식
    inputCondition: String? = null,
    // 입력 요소 앞의 아이콘
    leadingIcon: ImageVector? = null,
    // 우측 끝의 아이콘
    trailingIconMode: OutlinedTextFieldEndIconMode = OutlinedTextFieldEndIconMode.NONE,
    // 한줄 입력 여부
    singleLine: Boolean = false,
    // 상단 여백
    paddingTop: Dp = 0.dp,
    // 좌측 여백
    paddingStart: Dp = 0.dp,
    // 우측 여백
    paddingEnd: Dp = 0.dp,
    // 입력 모드
    inputType: OutlinedTextFieldInputType = OutlinedTextFieldInputType.TEXT,
    // 입력 가능 여부
    readOnly: Boolean = false,
    // 포커싱 관리
    focusRequest: MutableState<FocusRequester>? = null,
    // 입력 요소 하단에 나오는 메세지
    supportText: MutableState<String>? = null,
    // 에러 표시
    isError: MutableState<Boolean> = mutableStateOf(false),
    // 만약 입력에 대한 검사를 체크하는 기능이 필요하다면
    isCheckValue: MutableState<Boolean>? = null,
    onValueChanged: ((String) -> Unit)? = null,
) {
    var isShowingPasswordFlag by rememberSaveable { mutableStateOf(false) }

    val modifier = Modifier
        .fillMaxWidth()
        .padding(top = paddingTop, start = paddingStart, end = paddingEnd)
        .then(if (focusRequest != null) Modifier.focusRequester(focusRequest.value) else Modifier)

    val pretendard = FontFamily(Font(R.font.pretendardvariable))
    val visibilityIcon = ImageVector.vectorResource(R.drawable.visibility_24px)
    val visibilityOffIcon = ImageVector.vectorResource(R.drawable.visibility_off_24px)
    val textColor = colorResource(id = R.color.textColor8c8c8c)
    val grayColor = colorResource(id = R.color.colorD7D7D7)

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
        value = textFieldValue.value,
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
        onValueChange = {
            val filteredInput = inputCondition?.let { regex ->
                it.replace(regex.toRegex(), "")
            } ?: it

            textFieldValue.value = filteredInput
            onValueChanged?.invoke(filteredInput)
            isCheckValue?.value = false
        },
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = null) }
        },
        trailingIcon = when (trailingIconMode) {
            OutlinedTextFieldEndIconMode.NONE -> null
            OutlinedTextFieldEndIconMode.TEXT -> {
                {
                    if (textFieldValue.value.isNotEmpty()) {
                        IconButton(onClick = { textFieldValue.value = "" }) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                        }
                    }
                }
            }
            OutlinedTextFieldEndIconMode.PASSWORD -> {
                {
                    IconButton(onClick = { isShowingPasswordFlag = !isShowingPasswordFlag }) {
                        Icon(
                            imageVector = if (isShowingPasswordFlag) visibilityIcon else visibilityOffIcon,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        singleLine = singleLine,
        visualTransformation = if (!isShowingPasswordFlag && inputType == OutlinedTextFieldInputType.PASSWORD)
            PasswordVisualTransformation() else VisualTransformation.None,
        readOnly = readOnly,
        supportingText = supportText?.let {
            {
                Text(
                    text = it.value,
                    fontFamily = pretendard,
                    color = textColor
                )
            }
        },
        isError = isError.value,
        colors = textFieldColors
    )
}

enum class OutlinedTextFieldEndIconMode {
    NONE,
    TEXT,
    PASSWORD,
}

enum class OutlinedTextFieldInputType {
    TEXT,
    PASSWORD,
    NUMBER,
}
