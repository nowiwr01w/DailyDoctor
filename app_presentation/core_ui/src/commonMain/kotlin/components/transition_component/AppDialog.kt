package components.transition_component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import components.transition_component.TransitionComponentType.Dialog

@Composable
fun AppDialog(
    isDialogShown: MutableState<Boolean>,
    isCancellable: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    BaseTransitionComponent(
        type = Dialog,
        isComponentShown = isDialogShown,
        isCancellable = isCancellable,
        onDismissRequest = onDismissRequest,
        content = content
    )
}
