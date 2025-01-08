package nowiwr01p.daily.doctor.app_ui.dialogs

import androidx.compose.runtime.Composable
import navigation.config.child.DialogsChild
import navigation.config.child.DialogsChild.ExitAppChild
import navigation.config.child.DialogsChild.SelectLanguageChild
import nowiwr01p.daily.doctor.app_ui.dialogs.exit_app.ExitAppDialog
import nowiwr01p.daily.doctor.app_ui.dialogs.select_language.SelectLanguageDialog

@Composable
fun DialogsChild.getDialogContent(onDismiss: () -> Unit) = when (this) {
    is ExitAppChild -> ExitAppDialog(onDismiss)
    is SelectLanguageChild -> SelectLanguageDialog(onDismiss)
}
