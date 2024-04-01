package ui.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import base.theme.CustomTheme.colors
import dailydoctor.composeapp.generated.resources.Res
import dailydoctor.composeapp.generated.resources.ic_pin
import grayBackgroundColor
import greenColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import redColor
import secondaryBackgroundColor
import ui.core.ButtonState.DEFAULT
import ui.core.ButtonState.ERROR
import ui.core.ButtonState.INIT_LOADING
import ui.core.ButtonState.SEND_REQUEST
import ui.core.ButtonState.SUCCESS

/**
 * BUTTON
 */
@Composable
fun StateButton(
    text: String,
    modifier: Modifier = Modifier,
    state: ButtonState = DEFAULT,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val successErrorColor by animateColorAsState(
        targetValue = when (state) {
            ERROR -> MaterialTheme.colors.redColor
            SUCCESS -> MaterialTheme.colors.greenColor
            else -> MaterialTheme.colors.grayBackgroundColor
        },
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        )
    )
    val backgroundColor = when (state) {
        INIT_LOADING -> MaterialTheme.colors.secondaryBackgroundColor.copy(alpha = 0.9f)
        DEFAULT -> when {
            enabled -> MaterialTheme.colors.grayBackgroundColor
            else -> MaterialTheme.colors.secondaryBackgroundColor.copy(alpha = 0.9f)
        }
        SEND_REQUEST -> MaterialTheme.colors.grayBackgroundColor
        SUCCESS, ERROR -> successErrorColor
    }

    Button(
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = backgroundColor
        ),
        onClick = {
            if (state == DEFAULT) onClick.invoke()
        },
        modifier = modifier.clip(MaterialTheme.shapes.large)
    ) {
        when (state) {
            DEFAULT -> DefaultText(text, enabled, state)
            SUCCESS, ERROR -> AnimatedIcon(state)
            INIT_LOADING, SEND_REQUEST -> Progress(state)
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
    Text(
        text = text,
        style = MaterialTheme.typography.button,
        color = when (state) {
            DEFAULT, INIT_LOADING -> if (enabled) Color.White else MaterialTheme.colors.secondaryBackgroundColor
            else -> MaterialTheme.colors.secondaryBackgroundColor
        }
    )
}

/**
 * CIRCULAR PROGRESS
 */
@Composable
private fun Progress(state: ButtonState) = CircularProgressIndicator(
    strokeWidth = 2.dp,
    modifier = Modifier.size(20.dp),
    color = when {
        state == INIT_LOADING -> MaterialTheme.colors.secondaryBackgroundColor
        else -> Color.White
    }
)

/**
 * ANIMATE DRAWING ICON
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AnimatedIcon(state: ButtonState) {
    var setVisible by remember {
        mutableStateOf(false)
    }
    val alpha by animateFloatAsState(
        targetValue = if (setVisible) 1f else 0.25f,
        animationSpec = tween(500)
    )
    Image(
        painter = painterResource(
            if (state == SUCCESS) Res.drawable.ic_pin else Res.drawable.ic_pin
        ),
        contentDescription = "",
        colorFilter = ColorFilter.tint(colors.textColors.whiteTextColor),
        modifier = Modifier
            .size(20.dp)
            .alpha(if (setVisible) alpha else 0f)
    )
    LaunchedEffect(Unit) {
        setVisible = true
    }
}

/**
 * BUTTON STATE TYPES
 */
enum class ButtonState {
    INIT_LOADING,
    DEFAULT,
    SEND_REQUEST,
    SUCCESS,
    ERROR
}