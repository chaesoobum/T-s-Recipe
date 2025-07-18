package com.csb.presentation.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.component.colorBox.ColorBox
import com.csb.presentation.component.dialog.CustomProgressIndicator
import com.csb.presentation.component.spacer.Spacer_Height10DP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    rootScreenNavController: NavHostController,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.fetchRecipeScreen()
    }

    RecipeContent(
        rootScreenNavController,
        viewModel
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeContent(
    rootScreenNavController: NavHostController,
    viewModel: RecipeViewModel
) {
    if (!viewModel.isRecipeScreenShow.value) {
        CustomProgressIndicator(isShowing = true)
    } else {
        //상단바 높이 계산
        val paddingTop = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        val paddingTopHeight = 300.dp - paddingTop

        val scrollState = rememberScrollState()
        val maxScroll = with(LocalDensity.current) { paddingTopHeight.toPx() }
        val alpha = (scrollState.value / maxScroll).coerceIn(0f, 1f)
        val correctedAlpha = if (scrollState.value < 5) 0f else alpha

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RecipeHeader(
                    imageUrl = viewModel.recipeScreenData.value?.imageUrl,
                    title = viewModel.recipeScreenData.value?.title,
                    serving = viewModel.recipeScreenData.value?.portion,
                    price = viewModel.recipeScreenData.value?.price,
                    time = viewModel.recipeScreenData.value?.time,
                    name = viewModel.recipeScreenData.value?.name,
                    memo = viewModel.recipeScreenData.value?.memo,
                )

                PortionControl(
                    viewModel = viewModel
                )

                RecipeIngredient(
                    processedIngredient = viewModel.recipeScreenData.value?.ingredientList ?: emptyList()
                )

                RecipeIngredient(
                    isSource = true,
                    processedIngredient = viewModel.recipeScreenData.value?.sourceList ?: emptyList()
                )

                ColorBox()

                viewModel.recipeScreenData.value?.stepList?.forEach { it->
                    StepItem(it)
                    Spacer_Height10DP()
                    ColorBox()
                }
            }
            //스크롤로 동적으로 투명도 조절
            ActiveTopBar(
                paddingTop = paddingTop,
                correctedAlpha = correctedAlpha,
                routeScreenNavController = rootScreenNavController,
            )
        }
    }
}

@Composable
@Preview(showBackground = true, heightDp = 2000)
fun PreviewRecipeScreen() {
    RecipeScreen(
        rememberNavController(),
    )
}
