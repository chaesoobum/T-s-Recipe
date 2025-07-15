package com.csb.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    rootScreenNavController: NavHostController,
    viewModel: HomeViewModel
){
    LaunchedEffect(Unit) {
        viewModel.fetchHomeScreen()
    }

    HomeContent(
        rootScreenNavController,
        viewModel
    )
}

@Composable
fun HomeContent(
    rootScreenNavController: NavHostController,
    viewModel: HomeViewModel
){
    if (!viewModel.isHomeScreenShow.value){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(13.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = 8) {
                    RecipeItemShimmer()
                }
            }
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(13.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(viewModel.homeScreenData) { item ->
                    RecipeItem(
                        rootScreenNavController,
                        imageUrl = item.imageUrl,
                        title = item.title,
                        serving = item.portion,
                        price = item.price,
                        time = item.time,
                        name = item.name
                    )
                }
            }
        }
    }

}

//@Preview(
//    showBackground = true,
//    //heightDp = 2000 // 충분히 큰 높이 설정
//)
//@Composable
//fun PreviewHomeScreen() {
//    val homeViewModel: HomeViewModel = hiltViewModel()
//    HomeScreen(rememberNavController(),homeViewModel)
//}