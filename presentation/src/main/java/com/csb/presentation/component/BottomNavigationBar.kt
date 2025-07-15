package com.csb.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csb.presentation.R
import com.csb.presentation.util.MainScreen
import com.csb.presentation.util.RootScreen


@Composable
fun BottomNavigationBar(
    mainNavController: NavHostController,
    rootScreenNavController: NavHostController
) {
    val screens = listOf(
        MainScreen.Home,
        MainScreen.Search,
        MainScreen.UploadRecipe,
        MainScreen.MyPage,
    )
    val currentBackStack by mainNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    Column (
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ){

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier.height(60.dp)
        ) {
            screens.forEach { screen ->
                if (screen == MainScreen.UploadRecipe) {
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            rootScreenNavController.navigate(RootScreen.SCREEN_UPLOAD_RECIPE.name)
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_addrecipe),
                                contentDescription = "Add",
                                tint = colorResource(id = R.color.bottomNavUnselected),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        enabled = true,
                        alwaysShowLabel = false,
                        label = null,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent,
                            indicatorColor = Color.Transparent
                        )
                    )
                } else {
                    NavigationBarItem(
                        selected = currentRoute == screen.root,
                        onClick = {
                            if (currentRoute != screen.root) {
                                mainNavController.navigate(screen.root) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = screen.icon),
                                contentDescription = screen.label,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.bottomNavSelected),
                            unselectedIconColor = colorResource(id = R.color.bottomNavUnselected),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    }
}