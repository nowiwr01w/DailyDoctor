package ui.core_ui.helpers.snack_bar.data

import core.AppMessage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
sealed class SnackBarParams(
    open val type: SnackBarType,
    open val displayType: SnackBarDisplayType,
    open val duration: SnackBarDuration,
    open val direction: SnackBarDirection,
    open val message: AppMessage,
    open val actionText: StringResource? = null,
    open val actionCallback: () -> Unit = {},
    open val endCallback: () -> Unit = {}
) {
    data class ToolbarParams(
        override val type: SnackBarType,
        override val message: AppMessage,
        override val duration: SnackBarDuration = SnackBarDuration.Medium,
        override val endCallback: () -> Unit = {}
    ): SnackBarParams(
        message = message,
        duration = duration,
        type = type,
        direction = SnackBarDirection.Top,
        displayType = SnackBarDisplayType.STRAIGHT,
        endCallback = endCallback
    )

    data class TopFloatingParams(
        override val type: SnackBarType,
        override val message: AppMessage,
        override val duration: SnackBarDuration = SnackBarDuration.Medium,
        override val endCallback: () -> Unit = {}
    ): SnackBarParams(
        message = message,
        duration = duration,
        type = type,
        direction = SnackBarDirection.Top,
        displayType = SnackBarDisplayType.FULL_ROUNDED,
        endCallback = endCallback
    )
}