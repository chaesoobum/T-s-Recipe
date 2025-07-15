package com.csb.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.csb.presentation.component.SimpleOutlinedTextField
import com.csb.presentation.R

@Composable
fun SearchScreen(
    routeScreenNavController: NavHostController,
    searchViewModel: SearchViewModel
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val tempValue = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                SimpleOutlinedTextField(
                    singleLine = true,
                    placeHolder = stringResource(id = R.string.searchKeyowrds),
                    textFieldValue = tempValue
                )
            }

            if (tempValue.value.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.cancelEn),
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            tempValue.value = ""
                            focusManager.clearFocus()
                        }
                )
            }
        }
    }
}