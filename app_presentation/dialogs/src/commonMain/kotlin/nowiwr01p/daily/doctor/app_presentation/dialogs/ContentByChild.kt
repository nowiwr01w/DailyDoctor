package nowiwr01p.daily.doctor.app_presentation.dialogs

import androidx.compose.runtime.Composable
import navigation.config.child.DialogsChild
import navigation.config.child.DialogsChild.ExitAppChild
import nowiwr01p.daily.doctor.app_presentation.dialogs.exit_app.ExitAppDialog

@Composable
fun DialogsChild.getDialogContent(onDismiss: () -> Unit) = when (this) {
    is ExitAppChild -> ExitAppDialog(onDismiss)
}
