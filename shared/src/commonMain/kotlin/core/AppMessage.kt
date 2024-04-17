package core

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

sealed interface AppMessage {
    data class AppMessageText(val text: String): AppMessage
    @OptIn(ExperimentalResourceApi::class)
    data class AppMessageResource(val resId: StringResource): AppMessage
}