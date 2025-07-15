package com.csb.presentation.recipe

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.R
import com.csb.presentation.component.dialog.CustomProgressDialog
import com.csb.presentation.component.dialog.CustomProgressIndicator
import com.csb.presentation.home.HomeViewModel
import com.csb.presentation.home.RecipeHeader
import com.csb.presentation.home.RecipeIngredient

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
    if (!viewModel.isHomeScreenShow.value) {
        //TODO shimmer 화면만들기
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
                    imageUrl = viewModel.homeScreenData.value?.imageUrl,
                    title = "",
                    serving = "",
                    price = "",
                    time = "",
                    memo = "",
                    scrollState
                )
                Text(
                    text = stringResource(id = R.string.ingredient),
                    color = colorResource(id = R.color.textColor262626),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendardvariable)),
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_minus),
                            contentDescription = "n인분 줄이기",
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        text = "3${stringResource(id = R.string.serving)}",
                        color = colorResource(id = R.color.textColor262626),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardvariable)),
                    )
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_plus),
                            contentDescription = "n인분 추가하기",
                            tint = Color.Unspecified
                        )
                    }
                }
                RecipeIngredient()
                RecipeIngredient()

                val stepList = listOf("prep", "source", "cook")
                stepList.forEach {
                    StepItem(it)
                }
            }
            //스크롤로 동적으로 투명도 조절
            ActiveTopBar(
                paddingTop = paddingTop,
                correctedAlpha = correctedAlpha,
                routeScreenNavController = rootScreenNavController,
            )
            //스크롤로 동적으로 투명도 조절
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
