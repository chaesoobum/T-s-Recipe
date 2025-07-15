package com.csb.presentation.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton
import com.csb.presentation.component.OutlinedTextField
import com.csb.presentation.component.TopAppBar
import com.csb.presentation.util.RootScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    rootScreenNavController: NavHostController
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // 외부 터치 시 포커스를 해제하고 키보드 내리기
                detectTapGestures(onTap = {
                    focusManager.clearFocus() // 포커스를 해제
                })
            },
        topBar = {
            TopAppBar(
                isDivider = false,
                navigationIconImage = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                navigationIconOnClick = {
                    rootScreenNavController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.logIn),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                )
                Text(
                    text = stringResource(id = R.string.welcomeBack),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CustomBoxOutlineButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                    text = stringResource(id = R.string.continueWithGoogle),
                    icon = painterResource(id = R.drawable.ic_google_logo),
                    textFont = FontFamily(Font(R.font.roboto_regular)),
                    onClick = { },
                    buttonColor = Color.Transparent,
                    borderColor = colorResource(id = R.color.colorD9D9D9),
                    textColor = Color.Black,
                    isSameColor = false
                )
                CustomBoxOutlineButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                    text = stringResource(id = R.string.continueWithApple),
                    icon = painterResource(id = R.drawable.ic_apple_logo),
                    textFont = FontFamily(Font(R.font.roboto_regular)),
                    onClick = { },
                    buttonColor = Color.Transparent,
                    borderColor = colorResource(id = R.color.colorD9D9D9),
                    textColor = Color.Black,
                    isSameColor = false
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = stringResource(id = R.string.dontHaveAnAccount),
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.textColor262626)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        modifier = Modifier
                            .clickable{
                                rootScreenNavController.popBackStack()
                                rootScreenNavController.navigate(RootScreen.SCREEN_SIGNUP.name)
                            },
                        textAlign = TextAlign.Start,
                        text = stringResource(id = R.string.signUp),
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.color11A8F1)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
            }
        }
    }
}


@Preview(
    showBackground = true,
    heightDp = 2000
)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(rememberNavController())
}