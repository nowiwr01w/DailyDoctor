package observers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import contract.BaseEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

const val EFFECT_OBSERVER = "effect_observer"

@Composable
fun <T : BaseEffect> EffectObserver(effect: Flow<T>, onEach: (T) -> Unit) {
    LaunchedEffect(EFFECT_OBSERVER) {
        effect.onEach { onEach(it) }.collect()
    }
}