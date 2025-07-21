package com.csb.presentation.upload.stepBox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.csb.presentation.R
import com.csb.presentation.component.GetPhotoButton
import com.csb.presentation.component.SimpleOutlinedTextField
import com.csb.presentation.component.spacer.Spacer_Height10DP
import com.csb.presentation.component.spacer.Spacer_Width10DP
import com.csb.presentation.component.spacer.Spacer_Width20DP
import com.csb.presentation.upload.state.ProcedureInputBoxState

//과정 컴포넌트
@Composable
fun ProcedureInputBox(
    data: ProcedureInputBoxState,
    itemHeightPx:MutableState<Float>,
    modifier: Modifier,
    onDeleteProcedure: () -> Unit,
    onImageButtonClick: () -> Unit,
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
        Spacer_Height10DP()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer_Width20DP()
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_draganddrop),
                contentDescription = null,
                tint = colorResource(id = R.color.textColor262626),
            )
            Spacer_Width20DP()
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
            Spacer_Width10DP()
            GetPhotoButton(onClick = onImageButtonClick)
        }

        SimpleOutlinedTextField(
            textFieldValue = data.procedureContent,
            paddingTop = 10.dp,
            paddingStart = 10.dp,
            paddingEnd = 10.dp,
            placeHolder = stringResource(id = R.string.putWaterAndSteamerInAPotAndBoil)
        )
    }
}