package com.csb.presentation.upload.ingredientBox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csb.presentation.R
import com.csb.presentation.upload.rememberDragDropState
import com.csb.presentation.util.Tools.makeIngredientList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientInputTable(
    data: IngredientInputBoxState,
    itemHeightPx: MutableState<Float>,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit
) {
    val ingredientList = makeIngredientList(data)
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
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
                    .clickable{
                        focusManager.clearFocus()
                        onDelete()
                    }
            )

            ingredientList.forEach { item ->
                IngredientInputBox(
                    label = item.label,
                    width = item.width,
                    value = item.value,
                    placeholder = item.placeholder
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(0.5.dp)
                .background(color = colorResource(id = R.color.colorE9E9E9)))
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun PreviewIngredientInputTable() {
    val state = remember { IngredientInputBoxState() }
    val dummyHeight = remember { mutableStateOf(100f) } // 프리뷰용 기본 높이 설정
    val listState = rememberLazyListState()

    // 프리뷰용 임시 DragDropState
    val dummyDragDropState = rememberDragDropState(
        lazyListState = listState,
        onMove = { _, _ -> },
        draggableItemsNum = 1
    )

    IngredientInputTable(
        data = state,
        itemHeightPx = dummyHeight,
        onDelete = {}
    )
}