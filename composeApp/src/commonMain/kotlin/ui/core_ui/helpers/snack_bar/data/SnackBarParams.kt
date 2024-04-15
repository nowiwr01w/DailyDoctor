package ui.core_ui.helpers.snack_bar.data

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
data class SnackBarParams(
    val type: SnackBarType,
    val duration: SnackBarDuration,
    val direction: SnackBarDirection,
    val message: StringResource,
    val actionText: StringResource? = null,
    val actionCallback: (() -> Unit)? = null,
    val endCallback: (() -> Unit)? = null
)