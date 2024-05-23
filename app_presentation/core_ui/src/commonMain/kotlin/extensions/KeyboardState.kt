package extensions

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity

@Composable
fun isKeyboardOpened(): State<Boolean> {
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)
    val isKeyboardOpened = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(keyboardHeight) {
        isKeyboardOpened.value = keyboardHeight > 0
    }
    return isKeyboardOpened
}