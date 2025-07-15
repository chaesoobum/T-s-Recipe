package com.csb.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.csb.presentation.util.Tools
import com.valentinilk.shimmer.shimmer

@Composable
fun SubComposeAsyncImage(
    imgUrl:String,
    contentDescription:String,
    contentScale: ContentScale
) {
    SubcomposeAsyncImage(
        model = imgUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = Modifier.fillMaxSize()
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmer(Tools.rememberDefaultShimmer())
                )
            }

            is AsyncImagePainter.State.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                )
            }

            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}