package components.dialog

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
import androidx.compose.material.MaterialTheme.shapes
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
import components.LocalWindowInsets

const val DIALOG_TRANSITION_ANIMATION_DURATION = 500
private const val DIALOG_MIN_HEIGHT = 160

@Composable
fun AppDialog(
    isDialogShown: MutableState<Boolean>,
    isCancellable: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val dialogBounds = remember { mutableStateOf(Rect.Zero) }
    val dialogContentHeight = dialogBounds.value.height.dp / density.density
    val bottomPadding = LocalWindowInsets.current.bottomPadding + 24.dp
    val dialogContentHeightWithExtraPadding = run {
        max(DIALOG_MIN_HEIGHT.dp, dialogContentHeight) + 2 * bottomPadding
    }
    val containerBackgroundColor by animateColorAsState(
        targetValue = if (isDialogShown.value) Color.Black.copy(alpha = 0.6f) else Color.Transparent,
        animationSpec = tween(durationMillis = DIALOG_TRANSITION_ANIMATION_DURATION)
    )
    val outOfScreenOffset by animateDpAsState(
        targetValue = if (isDialogShown.value) 0.dp else dialogContentHeightWithExtraPadding,
        animationSpec = tween(
            durationMillis = DIALOG_TRANSITION_ANIMATION_DURATION,
            easing = if (isDialogShown.value) FastOutSlowInEasing else LinearEasing
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
                        if (!dialogBounds.value.contains(offset)) {
                            onDismissRequest()
                        }
                    }
                }
            }
    ) {
        DialogContainer(
            content = content,
            dialogBounds = dialogBounds,
            bottomPadding = bottomPadding
        )
    }

    LaunchedEffect(Unit) {
        isDialogShown.value = true
    }
}

@Composable
private fun BoxScope.DialogContainer(
    dialogBounds: MutableState<Rect>,
    bottomPadding: Dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = bottomPadding)
            .fillMaxWidth()
            .heightIn(min = DIALOG_MIN_HEIGHT.dp)
            .align(Alignment.BottomCenter)
            .clip(shapes.large)
            .background(
                color = Color.White,
                shape = shapes.large
            )
            .onGloballyPositioned { dialogCoordinates ->
                dialogBounds.value = dialogCoordinates.boundsInParent()
            }
            .padding(horizontal = 16.dp)
    ) {
        content()
    }
}
