package com.csb.presentation.main


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.component.BottomNavigationBar
import com.csb.presentation.home.HomeScreen
import com.csb.presentation.home.HomeViewModel
import com.csb.presentation.mypage.MyPageScreen
import com.csb.presentation.mypage.MyPageViewModel
import com.csb.presentation.search.SearchScreen
import com.csb.presentation.search.SearchViewModel
import com.csb.presentation.util.MainScreen


@Composable
fun MainScreen(rootScreenNavController: NavHostController) {
    val mainScreenNavController = rememberNavController()
    val currentBackStack by mainScreenNavController.currentBackStackEntryAsState()

    // 각 탭에 사용하는 뷰모델 선언
    val homeViewModel: HomeViewModel = hiltViewModel()
    val myPageViewModel: MyPageViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()


    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                mainNavController = mainScreenNavController,
                rootScreenNavController = rootScreenNavController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = mainScreenNavController,
            startDestination = MainScreen.Home.root,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                fadeIn(animationSpec = tween(0))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(0))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(0))
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(0))
            },
        ) {
            composable(
                MainScreen.Home.root
            ) {
                HomeScreen(rootScreenNavController,homeViewModel)
            }

            composable(
                MainScreen.MyPage.root
            ) {
                MyPageScreen(rootScreenNavController,myPageViewModel)
            }

            composable(
                MainScreen.Search.root
            ) {
                SearchScreen(rootScreenNavController,searchViewModel)
            }
        }
    }
}