package ui.core_ui.components.animation

import KottieAnimation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import ui.core_ui.components.animation.KottieAnimationPlayType.ENDLESSLY
import ui.core_ui.components.animation.KottieAnimationPlayType.ONCE
import ui.core_ui.components.animation.KottieCompositionSpecType.File
import ui.core_ui.components.animation.KottieCompositionSpecType.JsonString
import ui.core_ui.components.animation.KottieCompositionSpecType.Url

enum class KottieAnimationPlayType {
    ONCE,
    ENDLESSLY
}

sealed class KottieCompositionSpecType {
    data class Url(val url: String): KottieCompositionSpecType()
    data class File(val path: String): KottieCompositionSpecType()
    data class JsonString(val jsonString: String): KottieCompositionSpecType()
}

data class KottieAnimationSettings(
    val speed: Float = 1f,
    val isPlaying: Boolean = true,
    val playType: KottieAnimationPlayType = ONCE,
    val backgroundColor: Color = Color.Transparent
)

@Composable
fun KottieAnimationView(
    type: KottieCompositionSpecType,
    animationSettings: KottieAnimationSettings,
    modifier: Modifier = Modifier,
    onCompleteCallback: suspend () -> Unit = {}
) {
    val composition = rememberKottieComposition(
        spec = when (type) {
            is Url -> KottieCompositionSpec.Url(type.url)
            is File -> KottieCompositionSpec.File(type.path)
            is JsonString -> KottieCompositionSpec.JsonString(type.jsonString)
        }
    )
    val animationState by animateKottieCompositionAsState(
        composition = composition,
        isPlaying = animationSettings.isPlaying,
        speed = animationSettings.speed,
        restartOnPlay = animationSettings.playType == ENDLESSLY,
        iterations = if (animationSettings.playType == ENDLESSLY) Int.MAX_VALUE else 1
    )

    LaunchedEffect(animationState.isPlaying) {
        if (animationState.isCompleted) {
            onCompleteCallback()
        }
    }
    KottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { animationState.progress },
        backgroundColor = animationSettings.backgroundColor
    )
}