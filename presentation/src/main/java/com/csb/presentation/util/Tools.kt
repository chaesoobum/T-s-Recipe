package com.csb.presentation.util

import android.app.Activity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.csb.presentation.R
import com.csb.presentation.upload.ingredientBox.IngredientField
import com.csb.presentation.upload.state.IngredientInputBoxState
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.rememberShimmer

object Tools {

    //shimmer
    @Composable
    fun rememberDefaultShimmer(): Shimmer {
        return rememberShimmer(
            shimmerBounds = ShimmerBounds.View,
            theme = ShimmerTheme(
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 3000,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                blendMode = BlendMode.SrcOver,
                rotation = 0f,
                shaderColors = listOf(
                    Color.LightGray.copy(alpha = 0.1f),
                    Color.LightGray.copy(alpha = 0.1f),
                    Color.LightGray.copy(alpha = 0.1f)
                ),
                shaderColorStops = null,
                shimmerWidth = 200.dp
            )
        )
    }
    @Composable
    fun setLightStatusBarEffect() {
        val view = LocalView.current
        if (!view.isInEditMode) {
            val activity = view.context as Activity
            SideEffect {
                val window = activity.window
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
            }
        }
    }

    @Composable
    fun makeIngredientList(data: IngredientInputBoxState): List<IngredientField> {
        return listOf(
            IngredientField(
                label = stringResource(id = R.string.ingredientName),
                width = 160.dp,
                value = data.ingredientName,
                placeholder = stringResource(id = R.string.ingredientName)
            ),
            IngredientField(
                label = stringResource(id = R.string.Tbsp),
                width = 80.dp,
                value = data.ingredientTbsp,
                placeholder = stringResource(id = R.string.Tbsp)
            ),
            IngredientField(
                label = stringResource(id = R.string.qty),
                width = 80.dp,
                value = data.ingredientQty,
                placeholder = stringResource(id = R.string.qty)
            ),
            IngredientField(
                label = stringResource(id = R.string.cup),
                width = 80.dp,
                value = data.ingredientCup,
                placeholder = stringResource(id = R.string.cup)
            ),
            IngredientField(
                label = stringResource(id = R.string.g),
                width = 80.dp,
                value = data.ingredient_g,
                placeholder = stringResource(id = R.string.g)
            ),
            IngredientField(
                label = stringResource(id = R.string.ml),
                width = 80.dp,
                value = data.ingredient_ml,
                placeholder = stringResource(id = R.string.ml)
            ),
            IngredientField(
                label = stringResource(id = R.string.oz),
                width = 80.dp,
                value = data.ingredient_oz,
                placeholder = stringResource(id = R.string.oz)
            )
        )
    }
    @Composable
    fun exitApp(){
        var backPressedTime by remember { mutableStateOf(0L) }
        val context = LocalContext.current as ComponentActivity
        val exitText = stringResource(id = R.string.pressOneMoreTimeToExit)
        BackHandler {
            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime <= 2000) {
                context.finish()
            } else {
                backPressedTime = currentTime
                Toast.makeText(context, exitText, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    fun getColor(){

    }



}