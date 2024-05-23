package components.snack_bar

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.model.message.AppMessage
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import helpers.snack_bar.data.SnackBarDisplayType.BOTTOM_ROUNDED
import helpers.snack_bar.data.SnackBarDisplayType.FULL_ROUNDED
import helpers.snack_bar.data.SnackBarDisplayType.STRAIGHT
import helpers.snack_bar.data.SnackBarDisplayType.TOP_ROUNDED
import helpers.snack_bar.data.SnackBarParams
import helpers.snack_bar.data.SnackBarType.DEFAULT
import helpers.snack_bar.data.SnackBarType.ERROR
import helpers.snack_bar.data.SnackBarType.SUCCESS
import helpers.snack_bar.data.SnackBarType.WARNING
import components.LocalWindowInsets

private const val SNACK_BAR_ANIMATION_MILLIS = 500

@Composable
fun SnackBar(transition: Transition<SnackBarParams?>) {
    val snackBarParams = when {
        transition.targetState == null -> transition.currentState
        else -> transition.targetState
    }
    LaunchedEffect(snackBarParams?.duration) {
        snackBarParams?.duration?.timeMillis?.let { millis ->
            delay(millis + SNACK_BAR_ANIMATION_MILLIS) // + hide animation duration
            snackBarParams.endCallback()
        }
    }
    val statusBarHeight = LocalWindowInsets.current.topPadding
    val snackBarOffset = transition.animateDp(
        label = "Animate SnackBar Offset",
        transitionSpec = {
            tween(durationMillis = SNACK_BAR_ANIMATION_MILLIS)
        },
        targetValueByState = { currentParams ->
            if (currentParams == null) (-200).dp else 0.dp
        }
    )
    val shape = when (snackBarParams?.displayType) {
        STRAIGHT -> RoundedCornerShape(0.dp)
        TOP_ROUNDED -> RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        BOTTOM_ROUNDED -> RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        FULL_ROUNDED -> RoundedCornerShape(16.dp)
        null -> RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    }
    val backgroundColor = when (snackBarParams?.type) {
        DEFAULT -> Color(0xFF475569)
        SUCCESS -> Color(0xFF16A34A)
        WARNING -> Color(0xFFD97706)
        ERROR -> Color(0xFFE34446)
        null -> Color(0xFF32B153)
    }
    val minHeight = when (snackBarParams?.displayType) {
        FULL_ROUNDED -> 56.dp
        else -> 56.dp + statusBarHeight
    }
    val topPadding = when (snackBarParams?.displayType) {
        FULL_ROUNDED -> statusBarHeight
        else -> 0.dp
    }
    val horizontalPadding = when (snackBarParams?.displayType) {
        FULL_ROUNDED -> 16.dp
        else -> 0.dp
    }
    val alignment = when (snackBarParams?.displayType) {
        FULL_ROUNDED -> Alignment.CenterStart
        else -> Alignment.BottomStart
    }
    val topTextPadding = when (snackBarParams?.displayType) {
        FULL_ROUNDED -> 0.dp
        else -> statusBarHeight
    }
    val bottomTextPadding = when (snackBarParams?.displayType) {
        FULL_ROUNDED -> 0.dp
        else -> 8.dp
    }
    Box(
        modifier = Modifier
            .padding(start = horizontalPadding, end = horizontalPadding, top = topPadding)
            .fillMaxWidth()
            .heightIn(min = minHeight)
            .offset(y = snackBarOffset.value)
            .clip(shape)
            .background(backgroundColor)
    ) {
        val snackBarMessage = when (val message = snackBarParams?.message) {
            is AppMessage.AppMessageText -> message.text
            is AppMessage.AppMessageResource -> stringResource(message.resId)
            else -> ""
        }
        Text(
            text = snackBarMessage,
            color = Color.White,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = topTextPadding,
                    bottom = bottomTextPadding
                )
        )
    }
}