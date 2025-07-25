package com.csb.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.main.MainScreen
import com.csb.presentation.recipe.RecipeScreen
import com.csb.presentation.start.LoginScreen
import com.csb.presentation.start.SignUpScreen
import com.csb.presentation.start.StartScreen
import com.csb.presentation.upload.UploadRecipeScreen
import com.csb.presentation.util.RootScreen

//기본 뿌리 스크린
@Composable
fun RootScreen() {
    val rootScreenNavController = rememberNavController()

    NavHost(
        navController = rootScreenNavController,
        startDestination = RootScreen.SCREEN_MAIN.name,

        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(150)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(150)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(150)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(150)
            )
        }
    ) {
        composable(
            route = RootScreen.SCREEN_MAIN.name
        ) {
            MainScreen(rootScreenNavController)
        }

        composable(
            route = RootScreen.SCREEN_RECIPE.name,
        ) {
            RecipeScreen(rootScreenNavController)
        }

        composable(
            route = RootScreen.SCREEN_UPLOAD_RECIPE.name,
        ) {
            UploadRecipeScreen(rootScreenNavController)
        }

        composable(
            route = RootScreen.SCREEN_START.name
        ) {
            StartScreen(rootScreenNavController)

        }
        composable(
            route = RootScreen.SCREEN_LOGIN.name
        ) {
            LoginScreen(rootScreenNavController)

        }
        composable(
            route = RootScreen.SCREEN_SIGNUP.name
        ) {
            SignUpScreen(rootScreenNavController)

        }
    }
}
