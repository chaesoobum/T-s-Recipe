package com.csb.presentation.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.util.RootScreen
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton
import com.csb.presentation.component.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    rootScreenNavController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                isDivider = false,
                menuItems = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                            contentDescription = null
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )

                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo),
                        tint = colorResource(id = R.color.textColor262626),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    CustomBoxOutlineButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp),
                        text = stringResource(id = R.string.logIn),
                        onClick = {
                            rootScreenNavController.navigate(RootScreen.SCREEN_LOGIN.name)
                        },
                        buttonColor = colorResource(id = R.color.textColor262626),
                        borderColor = colorResource(id = R.color.textColor262626),
                        textColor = colorResource(id = R.color.white),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomBoxOutlineButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp),
                        text = stringResource(id = R.string.signUpForFree),
                        onClick = {
                            rootScreenNavController.navigate(RootScreen.SCREEN_SIGNUP.name)
                        },
                        buttonColor = Color.Transparent,
                        borderColor = colorResource(id = R.color.colorD9D9D9),
                        textColor = colorResource(id = R.color.textColor262626),
                    )
                }
            }
        }

    }
}

@Preview(
    showBackground = true,
    //heightDp = 2000 // 충분히 큰 높이 설정
)
@Composable
fun PreviewUploadRecipeScreen() {
    StartScreen(rememberNavController())
}