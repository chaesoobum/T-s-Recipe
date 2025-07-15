package com.csb.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csb.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakePictureOptionBottomSheet(
    onDismissRequest:()->Unit,
    sheetState:SheetState,
    cameraOnclick: ()->Unit,
    albumOnclick: ()->Unit,
) {

    ModalBottomSheet(
        onDismissRequest =onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            CustomBoxOutlineButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp,end = 10.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                text = stringResource(id = R.string.camera),
                onClick = cameraOnclick
            )
            CustomBoxOutlineButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp,end = 10.dp, top = 5.dp, bottom = 10.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                text = stringResource(id = R.string.album),
                onClick = albumOnclick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Previewbottom() {
    
}