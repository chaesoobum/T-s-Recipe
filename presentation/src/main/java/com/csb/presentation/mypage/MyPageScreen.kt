package com.csb.presentation.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MyPageScreen(
    routeScreenNavController: NavHostController,
    myPageViewModel: MyPageViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){ Text("MyPage") }
}