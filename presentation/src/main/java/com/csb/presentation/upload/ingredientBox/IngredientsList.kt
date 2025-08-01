package com.csb.presentation.upload.ingredientBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton
import com.csb.presentation.component.spacer.Spacer_Height10DP
import com.csb.presentation.upload.UploadRecipeViewModel
import com.csb.presentation.upload.dragContainerWithCustomDelay
import com.csb.presentation.upload.draggableItems
import com.csb.presentation.upload.rememberDragDropState

@Composable
fun IngredientsList(
    viewModel: UploadRecipeViewModel
) {
    val ingredientList = viewModel.formState.value.ingredientList
    val listState = rememberLazyListState()

    val handleCoordinatesMap = remember {
        mutableStateMapOf<String, MutableState<LayoutCoordinates?>>()
    }

    val dragDropState = rememberDragDropState(
        lazyListState = listState,
        draggableItemsNum = if (ingredientList.size > 1) ingredientList.size else 0,
        onMove = { from, to ->
            viewModel.moveIngredient(from, to)
        }
    )

    Spacer_Height10DP()

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        text = stringResource(id = R.string.basicIngredients),
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendard)),
        color = colorResource(id = R.color.textColor262626),
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )
    Spacer(
        modifier = Modifier.height(10.dp)
    )
    HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(2.dp)
            .background(color = colorResource(id = R.color.colorD9D9D9)))

    // 리스트 출력
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 9999.dp)
            .dragContainerWithCustomDelay(dragDropState,200L),
        state = listState
    ) {
        draggableItems(
            items = ingredientList,
            dragDropState = dragDropState,
            keySelector = { it.id },
            content = { modifier, item ->
                val itemHeightPx = remember { mutableFloatStateOf(0f) }
                IngredientInputTable(
                    data = item,
                    itemHeightPx = itemHeightPx,
                    modifier = modifier,
                    onDelete = { viewModel.removeIngredient(item) },
                )
            }
        )
    }
    CustomBoxOutlineButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
        text = stringResource(id = R.string.addIngredient),
        icon = painterResource(id = R.drawable.add_circle_24px),
        onClick = {
            viewModel.addIngredientBox()
        },
        buttonColor = colorResource(id = R.color.textColor262626),
        borderColor = colorResource(id = R.color.textColor262626),
        textColor = MaterialTheme.colorScheme.background,
    )
    Spacer(modifier = Modifier.height(10.dp))

}