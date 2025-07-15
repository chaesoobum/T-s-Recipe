package com.csb.presentation.component.photo

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.csb.presentation.R

@Composable
fun PhotoItem(
    imageUri: Uri? = Uri.EMPTY,
    deleteButton:()->Unit
) {
    Column(
        modifier = Modifier
            .size(120.dp)
    ) {
        Row(
            modifier = Modifier
                .height(20.dp)
        ) {
            Box(modifier = Modifier.width(90.dp))
            Box(modifier = Modifier.width(30.dp)) {
                IconButton(
                    modifier = Modifier
                        .fillMaxSize(),
                    onClick = deleteButton
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_img),
                        contentDescription = "삭제",
                        tint = Color.Unspecified
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .height(100.dp)
        ) {
            Box (
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.colorD7D7D7),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(RoundedCornerShape(5.dp))
            ) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun previewPhotoItem() {
    PhotoItem(Uri.EMPTY,{})
}