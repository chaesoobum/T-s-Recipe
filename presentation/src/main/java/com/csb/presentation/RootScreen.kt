package com.csb.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.main.MainScreen
import com.csb.presentation.recipe.RecipeScreen
import com.csb.presentation.start.LoginScreen
import com.csb.presentation.start.SignUpScreen
import com.csb.presentation.start.StartScreen
import com.csb.presentation.upload.UploadRecipeScreen
import com.csb.presentation.upload.UploadRecipeViewModel
import com.csb.presentation.util.RootScreen

@Composable
fun RootScreen() {
    val rootScreenNavController = rememberNavController()

    NavHost(
        navController = rootScreenNavController,
        startDestination = RootScreen.SCREEN_MAIN.name,

        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(250)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(250)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(250)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(250)
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
            StartScreen(navController = rootScreenNavController)

        }
        composable(
            route = RootScreen.SCREEN_LOGIN.name
        ) {
            LoginScreen(navController = rootScreenNavController)

        }
        composable(
            route = RootScreen.SCREEN_SIGNUP.name
        ) {
            SignUpScreen(navController = rootScreenNavController)

        }
    }
}
