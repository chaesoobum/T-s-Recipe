package com.csb.presentation.upload.stepBox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton
import com.csb.presentation.component.SimpleOutlinedTextField
import com.csb.presentation.component.spacer.Spacer_Height10DP
import com.csb.presentation.upload.dragContainerWithCustomDelay
import com.csb.presentation.upload.draggableItems
import com.csb.presentation.upload.rememberDragDropState
import com.csb.presentation.upload.state.ProcedureInputBoxState

@Composable
fun StepBox(
    stepState: StepState,
    addProcedure: () -> Unit,
    onImageButtonClick: () -> Unit,
    onDeleteStep: () -> Unit,
    onDeleteProcedure: (ProcedureInputBoxState) -> Unit,
    moveProcedure: (Int,Int)->Unit,
    ) {
    val procedureList = stepState.procedureList
    val listState = rememberLazyListState()

    val handleCoordinatesMap = remember {
        mutableStateMapOf<String, MutableState<LayoutCoordinates?>>()
    }

    val dragDropState = rememberDragDropState(
        lazyListState = listState,
        draggableItemsNum = if (procedureList.size > 1) procedureList.size else 0,
        onMove = { from, to ->
            moveProcedure(from,to)
        }
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        text = stringResource(id = R.string.detailedSteps),
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendard)),
        color = colorResource(id = R.color.textColor262626),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .weight(5f)
        ) {
            SimpleOutlinedTextField(
                textFieldValue = stepState.stepName,
                singleLine = true,
                placeHolder = "${stringResource(id = R.string.step)}${stepState.stepIndex + 1}"
            )
        }
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
                contentDescription = null,
                tint = colorResource(id = R.color.textColor262626),
                modifier = Modifier
                    .clickable {
                        onDeleteStep()
                    }
            )
        }
    }


    HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(2.dp)
            .background(color = colorResource(id = R.color.colorD9D9D9))
    )


    // 리스트 출력
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 9999.dp)
            .dragContainerWithCustomDelay(dragDropState,200L),
        state = listState
    ) {
        draggableItems(
            items = procedureList,
            dragDropState = dragDropState,
            keySelector = { it.id },
            content = { modifier, item ->
                val itemHeightPx = remember { mutableFloatStateOf(0f) }
                ProcedureInputBox(
                    data = item,
                    itemHeightPx = itemHeightPx,
                    modifier = modifier,
                    onDeleteProcedure = { onDeleteProcedure(item) },
                    onImageButtonClick = onImageButtonClick,
                )
            },
        )
    }
    Spacer_Height10DP()

    CustomBoxOutlineButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        text = stringResource(id = R.string.addDetailProcess),
        icon = painterResource(id = R.drawable.add_circle_24px),
        onClick = addProcedure,
        buttonColor = Color.Transparent,
        borderColor = colorResource(id = R.color.color7B7B7B),
        textColor = colorResource(id = R.color.textColor262626),
    )

    Spacer_Height10DP()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(color = colorResource(id = R.color.colorE9E9E9))
    )
}