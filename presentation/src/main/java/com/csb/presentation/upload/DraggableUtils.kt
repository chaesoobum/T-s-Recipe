package com.csb.presentation.upload

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitLongPressOrCancellation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel


@OptIn(ExperimentalFoundationApi::class)
inline fun <T : Any> LazyListScope.draggableItems(
    items: List<T>,
    dragDropState: DragDropState,
    crossinline content: @Composable (Modifier, T) -> Unit,
    noinline keySelector: ((T) -> Any)? = null
) {
    itemsIndexed(
        items = items,
        key = if (keySelector != null) { _, item -> keySelector(item) } else null,
        contentType = { index, _ -> DraggableItem(index = index) })
    { index, item ->
        val modifier = if (dragDropState.draggingItemIndex == index) {
            Modifier
                .zIndex(1f)
                .graphicsLayer {
                    translationY = dragDropState.delta
                }
                .shadow(15.dp)
        } else {
            Modifier.animateItem(
                fadeInSpec = tween(500),
                fadeOutSpec = tween(50),
            )
        }
        content(modifier, item)
    }
}

fun Modifier.dragContainerWithCustomDelay(
    dragDropState: DragDropState,
    delayMillis: Long = 300L
): Modifier = pointerInput(dragDropState) {
    forEachGesture {
        awaitPointerEventScope {
            val down = awaitFirstDown()
            var dragStarted = false

            val longPress = try {
                withTimeout(delayMillis) {
                    awaitLongPressOrCancellation(down.id)
                }
            } catch (e: TimeoutCancellationException) {
                null
            }

            if (longPress != null) {
                dragDropState.onDragStart(longPress.position)
                dragStarted = true
            }

            if (dragStarted) {
                // 수동 drag 처리
                while (true) {
                    val event = awaitPointerEvent()
                    val drag = event.changes.firstOrNull()
                    if (drag != null && drag.pressed) {
                        val dragAmount = drag.positionChange()
                        drag.consume()
                        dragDropState.onDrag(dragAmount)
                    } else {
                        dragDropState.onDragInterrupted()
                        break
                    }
                }
            }
        }
    }
}

@Composable
fun rememberDragDropState(
    lazyListState: LazyListState,
    onMove: (Int, Int) -> Unit,
    draggableItemsNum: Int
): DragDropState {

    val state =
        remember(lazyListState) {
            DragDropState(
                draggableItemsNum = draggableItemsNum,
                stateList = lazyListState,
                onMove = onMove,
            )
        }
    LaunchedEffect(state) {
        while (true) {
            val diff = state.scrollChannel.receive()
            lazyListState.scrollBy(diff)
        }
    }
    return state
}

class DragDropState(
    private val draggableItemsNum: Int,
    private val stateList: LazyListState,
    private val onMove: (Int, Int) -> Unit,
) {
    var draggingItemIndex: Int? by mutableStateOf(null)

    var delta by mutableFloatStateOf(0f)

    val scrollChannel = Channel<Float>()

    private var draggingItem: LazyListItemInfo? = null

    internal fun onDragStart(offset: Offset) {
        val listStart = stateList.layoutInfo.viewportStartOffset
        val relativeY = offset.y.toInt() - listStart

        stateList.layoutInfo.visibleItemsInfo
            .firstOrNull { item ->
                relativeY in item.offset..(item.offset + item.size)
            }?.also {
                (it.contentType as? DraggableItem)?.let { draggableItem ->
                    draggingItem = it
                    draggingItemIndex = draggableItem.index
                }
            }
    }

    internal fun onDragInterrupted() {
        draggingItem = null
        draggingItemIndex = null
        delta = 0f
    }

    internal fun onDrag(offset: Offset) {
        delta += offset.y

        val currentDraggingItemIndex = draggingItemIndex ?: return
        val currentDraggingItem = draggingItem ?: return

        val startOffset = currentDraggingItem.offset + delta
        val endOffset = currentDraggingItem.offset + currentDraggingItem.size + delta
        val middleOffset = startOffset + (endOffset - startOffset) / 2

        val viewportStart = stateList.layoutInfo.viewportStartOffset
        val viewportEnd = stateList.layoutInfo.viewportEndOffset

        if (endOffset < viewportStart || startOffset > viewportEnd) {
            onDragInterrupted()
            return
        }

        val targetItem = stateList.layoutInfo.visibleItemsInfo.find { item ->
            middleOffset.toInt() in item.offset..(item.offset + item.size) &&
                    currentDraggingItem.index != item.index &&
                    item.contentType is DraggableItem
        }

        if (targetItem != null) {
            val targetIndex = (targetItem.contentType as DraggableItem).index
            onMove(currentDraggingItemIndex, targetIndex)
            draggingItemIndex = targetIndex
            delta += currentDraggingItem.offset - targetItem.offset
            draggingItem = targetItem
        } else {
            val startOffsetToTop = startOffset - viewportStart
            val endOffsetToBottom = endOffset - viewportEnd
            val scroll = when {
                startOffsetToTop < 0 -> startOffsetToTop.coerceAtMost(0f)
                endOffsetToBottom > 0 -> endOffsetToBottom.coerceAtLeast(0f)
                else -> 0f
            }

            if (scroll != 0f && currentDraggingItemIndex != 0 && currentDraggingItemIndex != draggableItemsNum - 1) {
                scrollChannel.trySend(scroll)
            }
        }
    }

}

data class DraggableItem(val index: Int)