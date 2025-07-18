package com.csb.presentation.upload.stepBox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.csb.presentation.component.GetPhotoButton
import com.csb.presentation.component.SimpleOutlinedTextField
import com.csb.presentation.R


@Composable
fun ProcedureInputBox(
    modifier: Modifier,
    itemHeightPx:MutableState<Float>,
    data: ProcedureInputBoxState,
    onImageButtonClick: () -> Unit,
    onDeleteProcedure: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .clipToBounds()
            .onGloballyPositioned { layoutCoordinates ->
            itemHeightPx.value = layoutCoordinates.size.height.toFloat()
        }
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_draganddrop),
                contentDescription = null,
                tint = colorResource(id = R.color.textColor262626),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ingredientdelete),
                contentDescription = null,
                tint = colorResource(id = R.color.textColor262626),
                modifier = Modifier
                    .clickable {
                        focusManager.clearFocus()
                        onDeleteProcedure()
                    }
            )
            Spacer(modifier = Modifier.width(10.dp))
            GetPhotoButton(onClick = onImageButtonClick)
        }

        SimpleOutlinedTextField(
            textFieldValue = data.procedureContent,
            paddingTop = 10.dp,
            paddingStart = 10.dp,
            paddingEnd = 10.dp,
            placeHolder = stringResource(id = R.string.putWaterAndSteamerInAPotAndBoil)
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp)
                .height(0.5.dp)
                .background(color = colorResource(id = R.color.colorE9E9E9))
        )
    }
}