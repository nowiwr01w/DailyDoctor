package components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import theme.CustomTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.animation.KottieAnimationSettings
import components.animation.KottieAnimationView
import components.animation.KottieCompositionSpecType
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.ERROR
import components.button.ButtonState.ORANGE_ACTIVE
import components.button.ButtonState.LIGHT_GRAY_ACTIVE
import components.button.ButtonState.DARK_GRAY_PROGRESS
import components.button.ButtonState.ORANGE_ACTIVE_PROGRESS
import components.button.ButtonState.SUCCESS
import components.button.animations.errorAnimationJson
import components.button.animations.successAnimationJson
import platform.Platform
import platform.currentPlatform
import theme.CustomTheme.colors

/**
 * BUTTON
 */
@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    state: ButtonState = DARK_GRAY_ACTIVE,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val successErrorColor by animateColorAsState(
        targetValue = when (state) {
            ERROR -> Color(0xFFE34446)
            SUCCESS -> Color(0xFF16A34A)
            else -> Color(0xFF3f4257)
        },
        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
    )
    val backgroundColor = when (state) {
        ORANGE_ACTIVE, ORANGE_ACTIVE_PROGRESS -> colors.backgroundColors.redBackgroundColor
        LIGHT_GRAY_ACTIVE -> Color(0xFFF2F1F1)
        DARK_GRAY_ACTIVE -> when {
            enabled -> Color(0xFF3f4257)
            else -> Color(0xFF888CAB).copy(alpha = 0.9f)
        }
        DARK_GRAY_PROGRESS -> Color(0xFF3f4257)
        SUCCESS, ERROR -> successErrorColor
    }

    val shape = when (currentPlatform) {
        Platform.WEB -> CustomTheme.shapes.medium
        else -> CustomTheme.shapes.large
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .background(
                shape = shape,
                color = backgroundColor
            )
            .clickable(enabled = enabled) {
                when (state) {
                    DARK_GRAY_ACTIVE, ORANGE_ACTIVE, LIGHT_GRAY_ACTIVE -> onClick.invoke()
                    else -> Unit
                }
            }
    ) {
        when (state) {
            DARK_GRAY_ACTIVE, ORANGE_ACTIVE, LIGHT_GRAY_ACTIVE -> DefaultText(
                text = text,
                enabled = enabled,
                state = state
            )
            SUCCESS, ERROR -> AnimatedIcon(
                state = state,
                backgroundColor = backgroundColor
            )
            DARK_GRAY_PROGRESS, ORANGE_ACTIVE_PROGRESS -> Progress()
        }
    }
}

/**
 * BUTTON TEXT
 */
@Composable
private fun DefaultText(
    text: String,
    enabled: Boolean,
    state: ButtonState
) {
    val textColor = when (state) {
        LIGHT_GRAY_ACTIVE -> colors.textColors.blackTextColor
        else -> when {
            enabled -> colors.textColors.whiteTextColor
            else -> Color(0xFF888CAB)
        }
    }
    Text(
        text = text,
        style = CustomTheme.typography.headlineMedium,
        color = textColor
    )
}

/**
 * CIRCULAR PROGRESS
 */
@Composable
private fun Progress() = CircularProgressIndicator(
    strokeWidth = 2.dp,
    modifier = Modifier.size(20.dp),
    color = colors.textColors.whiteTextColor
)

/**
 * ANIMATE DRAWING ICON
 */
@Composable
private fun AnimatedIcon(
    state: ButtonState,
    backgroundColor: Color // TODO: https://github.com/ismai117/kottie/issues/5
) {
    val animationJsonString = when (state) { // TODO: Find out how to work with files in KMM
        ERROR -> errorAnimationJson
        SUCCESS -> successAnimationJson
        else -> throw IllegalStateException("Wrong ButtonState")
    }
    KottieAnimationView(
        type = KottieCompositionSpecType.JsonString(animationJsonString),
        animationSettings = KottieAnimationSettings(backgroundColor = backgroundColor),
        modifier = Modifier.size(24.dp)
    )
}

/**
 * BUTTON STATE TYPES
 */
enum class ButtonState {
    ORANGE_ACTIVE,
    ORANGE_ACTIVE_PROGRESS,
    LIGHT_GRAY_ACTIVE,
    DARK_GRAY_ACTIVE,
    DARK_GRAY_PROGRESS,
    SUCCESS,
    ERROR
}

