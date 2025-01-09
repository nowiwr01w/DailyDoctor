package components.transition_component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import components.transition_component.TransitionComponentType.BottomSheet

@Composable
fun AppBottomSheet(
    isBottomSheetShown: MutableState<Boolean>,
    isCancellable: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    BaseTransitionComponent(
        type = BottomSheet,
        isComponentShown = isBottomSheetShown,
        isCancellable = isCancellable,
        onDismissRequest = onDismissRequest,
        content = content
    )
}
