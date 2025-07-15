package com.csb.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    rootScreenNavController: NavHostController,
    viewModel: HomeViewModel
) {
    if (viewModel.isHomeScreenShow.value == true){
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

                items(viewModel.temp) { item ->
                    RecipeItem(
                        rootScreenNavController,
                        imageUrl = item[0],
                        title = item[1],
                        serving = item[2],
                        price = item[3],
                        time = item[4],
                        name = item[5]
                    )
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

                items(viewModel.tempTemp) { item ->
                    RecipeItemShimmer()
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