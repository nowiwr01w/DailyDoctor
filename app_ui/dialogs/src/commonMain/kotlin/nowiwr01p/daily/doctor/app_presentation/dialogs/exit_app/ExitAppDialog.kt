package nowiwr01p.daily.doctor.app_presentation.dialogs.exit_app

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.app_presentation.dialogs.BaseDialogContent
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.cancel
import nowiwr01p.daily.doctor.resources.close_app_subtitle
import nowiwr01p.daily.doctor.resources.close_app_title
import nowiwr01p.daily.doctor.resources.exit

@Composable
fun ExitAppDialog(onDismiss: () -> Unit) = BaseDialogContent(
    title = Res.string.close_app_title,
    subtitle = Res.string.close_app_subtitle,
    leftButtonText = Res.string.exit,
    onLeftButtonClick = onDismiss, // TODO: Add logic later
    rightButtonText = Res.string.cancel,
    onRightButtonClick = onDismiss
)
