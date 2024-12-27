package components.transition_component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.times
import components.transition_component.TransitionComponentType.BottomSheet
import components.transition_component.TransitionComponentType.Dialog
import constants.COMPONENT_TRANSITION_ANIMATION_DURATION
import getScreenHeight
import theme.LocalWindowInsets

private const val COMPONENT_MIN_HEIGHT = 160

@Composable
internal fun BaseTransitionComponent(
    type: TransitionComponentType,
    isComponentShown: MutableState<Boolean>,
    isCancellable: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val componentBounds = remember { mutableStateOf(Rect.Zero) }
    val bottomPadding = getBottomPadding(type)
    val contentOutOfScreenOffset = getContentOffset(type, componentBounds, bottomPadding)
    val containerBackgroundColor by animateColorAsState(
        targetValue = if (isComponentShown.value) Color.Black.copy(alpha = 0.6f) else Color.Transparent,
        animationSpec = tween(durationMillis = COMPONENT_TRANSITION_ANIMATION_DURATION)
    )
    val outOfScreenOffset by animateDpAsState(
        targetValue = if (isComponentShown.value) 0.dp else contentOutOfScreenOffset,
        animationSpec = tween(
            durationMillis = COMPONENT_TRANSITION_ANIMATION_DURATION,
            easing = if (isComponentShown.value) FastOutSlowInEasing else LinearEasing
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(containerBackgroundColor)
            .offset(y = outOfScreenOffset)
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    if (isCancellable) {
                        if (!componentBounds.value.contains(offset)) {
                            onDismissRequest()
                        }
                    }
                }
            }
    ) {
        ComponentContainer(
            type = type,
            componentBounds = componentBounds,
            bottomPadding = bottomPadding,
            content = content
        )
    }

    LaunchedEffect(Unit) {
        isComponentShown.value = true
    }
}

/**
 * COMPONENT CONTAINER
 */
@Composable
private fun BoxScope.ComponentContainer(
    type: TransitionComponentType,
    componentBounds: MutableState<Rect>,
    bottomPadding: Dp,
    content: @Composable () -> Unit
) {
    val shape = when (type) {
        is Dialog -> RoundedCornerShape(24.dp)
        is BottomSheet -> RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    }
    val horizontalPadding = when (type) {
        is Dialog -> 16.dp
        is BottomSheet -> 0.dp
    }
    Box(
        modifier = Modifier
            .padding(start = horizontalPadding, end = horizontalPadding, bottom = bottomPadding)
            .fillMaxWidth()
            .heightIn(min = COMPONENT_MIN_HEIGHT.dp)
            .align(Alignment.BottomCenter)
            .clip(shape)
            .background(
                color = Color.White,
                shape = shape
            )
            .onGloballyPositioned { componentCoordinates ->
                componentBounds.value = componentCoordinates.boundsInParent()
            }
            .padding(horizontal = horizontalPadding)
    ) {
        content()
    }
}

/**
 * COMPONENT UI SETTINGS
 */
@Composable
private fun getContentOffset(
    type: TransitionComponentType,
    componentBounds: MutableState<Rect>,
    bottomPadding: Dp
): Dp {
    val density = LocalDensity.current
    val componentContentHeight = componentBounds.value.height.dp / density.density
    val adjustedComponentContentHeight = max(COMPONENT_MIN_HEIGHT.dp, componentContentHeight)
    return getBottomOffset(
        type = type,
        adjustedComponentContentHeight = adjustedComponentContentHeight,
        bottomPadding = bottomPadding
    )
}

@Composable
private fun getBottomOffset(
    type: TransitionComponentType,
    adjustedComponentContentHeight: Dp,
    bottomPadding: Dp
): Dp {
    val dialogOffset = adjustedComponentContentHeight + 2 * bottomPadding
    return when (type) {
        is Dialog -> dialogOffset
        is BottomSheet -> getScreenHeight()
    }
}

@Composable
private fun getBottomPadding(type: TransitionComponentType) = when (type) {
    is Dialog -> LocalWindowInsets.current.bottomPadding + 24.dp
    else -> 0.dp
}

/**
 * COMPONENT TYPE
 */
sealed interface TransitionComponentType {
    data object BottomSheet: TransitionComponentType
    data object Dialog: TransitionComponentType
}
