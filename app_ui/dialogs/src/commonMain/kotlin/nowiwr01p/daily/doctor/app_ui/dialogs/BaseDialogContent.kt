package nowiwr01p.daily.doctor.app_ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import theme.CustomTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.button.ButtonState.LIGHT_GRAY_ACTIVE
import components.button.ButtonState.ORANGE_ACTIVE
import components.button.AppButton
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import theme.CustomTheme.colors

@Composable
internal fun BaseDialogContent(
    title: StringResource,
    subtitle: StringResource,
    leftButtonText: StringResource,
    onLeftButtonClick: () -> Unit,
    rightButtonText: StringResource,
    onRightButtonClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        /** TITLE **/
        Text(
            text = stringResource(title),
            color = colors.textColors.blackTextColor,
            style = CustomTheme.typography.displaySmall
        )
        /** SUBTITLE **/
        Text(
            text = stringResource(subtitle),
            color = colors.textColors.grayTextColor,
            style = CustomTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            /** LEFT BUTTON **/
            AppButton(
                text = stringResource(leftButtonText),
                state = LIGHT_GRAY_ACTIVE,
                onClick = onLeftButtonClick,
                modifier = Modifier
                    .height(48.dp)
                    .weight(0.5f)
            )
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            /** RIGHT BUTTON **/
            AppButton(
                text = stringResource(rightButtonText),
                state = ORANGE_ACTIVE,
                onClick = onRightButtonClick,
                modifier = Modifier
                    .height(48.dp)
                    .weight(0.5f)
            )
        }
    }
}
