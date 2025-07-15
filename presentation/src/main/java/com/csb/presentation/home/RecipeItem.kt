package com.csb.presentation.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.R
import com.csb.presentation.component.SubComposeAsyncImage
import com.csb.presentation.recipe.sharedviewmodel.RecipeSharedViewModel
import com.csb.presentation.util.RootScreen


@Composable
fun RecipeItem(
    routeScreenNavController: NavHostController,
    imageUrl: String,
    title: String,
    serving: String,
    price: String,
    time: String,
    name: String
) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val recipeSharedViewModel: RecipeSharedViewModel = hiltViewModel(activity)

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable {
                recipeSharedViewModel.setRecipeData(
                    imageUrl = imageUrl,
                    title = title,
                    serving = serving,
                    price = price,
                    time = time,
                    name = name
                )
                routeScreenNavController.navigate(RootScreen.SCREEN_RECIPE.name)
            }
    ) {
        //Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(horizontal = 16.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.Transparent)
        ) {
            SubComposeAsyncImage(
                imgUrl = imageUrl,
                contentDescription = "레시피 이미지",
                contentScale = ContentScale.Crop
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
                .padding(start = 10.dp,end = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                fontSize = 16.sp,
                color = colorResource(id = R.color.textColor262626),
                fontFamily = FontFamily(Font(R.font.pretendardvariable)),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${serving}${stringResource(id = R.string.serving)} · ${price}${stringResource(id = R.string.price)} · ${time}${stringResource(id = R.string.minute)}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.textColor8c8c8c),
                fontFamily = FontFamily(Font(R.font.pretendardvariable)),
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = name,
                color = colorResource(id = R.color.textColor8c8c8c),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendardvariable)),
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PreviewRecipeItem() {
    RecipeItem(
        rememberNavController(),
        imageUrl = "url",
        title = "연근 조림",
        serving = "3",
        price = "4,000",
        time = "15",
        name = "홍길동"
    )
}