package core.model.message

import org.jetbrains.compose.resources.StringResource

sealed interface AppMessage {
    data class AppMessageText(val text: String): AppMessage
    data class AppMessageResource(val resId: StringResource): AppMessage
}