package nowiwr01p.daily.doctor.app_presentation.dialogs.select_language

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.button.AppButton
import components.image.AppImage
import navigation.config.child.DialogsChild.SelectLanguageChild
import navigation.screen_results.ScreenResultKey.SelectLanguageResultKey
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Effect.CloseDialog
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Event.OnConfirmSelectedLanguage
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Event.OnSelectLanguageClicked
import nowiwr01p.daily.doctor.new_resources.language.Language
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_done
import nowiwr01p.daily.doctor.resources.language_title
import org.jetbrains.compose.resources.stringResource
import theme.CustomTheme.colors
import theme.CustomTheme.typography
import view_model.rememberViewModel

@Composable
internal fun SelectLanguageChild.SelectLanguageDialog(
    onDismiss: () -> Unit,
    viewModel: SelectLanguageViewModel = baseComponent.rememberViewModel()
) {
    val listener = object : Listener {
        override fun onCloseClick() {
            onDismiss()
        }
        override fun onSelectLanguageClick(language: Language) {
            viewModel.setEvent(OnSelectLanguageClicked(language))
        }
        override fun onConfirmSelectedLanguage() {
            viewModel.setEvent(OnConfirmSelectedLanguage)
        }
    }
    val state = viewModel.getState { effect ->
        when (effect) {
            is CloseDialog -> {
                resultHandler.setScreenResult(SelectLanguageResultKey, effect.selectedLanguage)
                onDismiss()
            }
        }
    }
    Content(
        state = state,
        listener = listener
    )
}

@Composable
private fun Content(
    state: State,
    listener: Listener?
) {
    Column(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Title()
        LanguagesList(state, listener)
        SelectButton(state, listener)
    }
}

/**
 * TITLE AND CLOSE BUTTON
 */
@Composable
private fun Title() = Text(
    text = stringResource(Res.string.language_title),
    color = colors.textColors.blackTextColor,
    style = typography.displaySmall,
    textAlign = TextAlign.Center,
    modifier = Modifier.fillMaxWidth()
)

/**
 * LANGUAGES LIST
 */
@Composable
private fun LanguagesList(
    state: State,
    listener: Listener?
) {
    Column(
        modifier = Modifier.padding(top = 12.dp, bottom = 16.dp)
    ) {
        val items = state.allLanguages
        items.forEachIndexed { index, language ->
            LanguageItem(
                language = language,
                isSelected = language.code == state.selectedLanguage.code,
                onSelectLanguageClick = { listener?.onSelectLanguageClick(language) }
            )
        }
    }
}

@Composable
private fun LanguageItem(
    language: Language,
    isSelected: Boolean,
    onSelectLanguageClick: () -> Unit
) {
    val languageCheckColor by animateColorAsState(
        targetValue = when {
            isSelected -> colors.backgroundColors.grayBackgroundColor
            else -> Color.Transparent
        },
        animationSpec = tween()
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onSelectLanguageClick() }
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = language.name,
            color = colors.textColors.blackTextColor.copy(alpha = 0.75f),
            style = typography.bodyMedium,
            modifier = Modifier.padding(start = 12.dp)
        )
        Text(
            text = "(${language.code.uppercase()})",
            style = typography.bodySmall,
            color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        AppImage(
            image = Res.drawable.ic_done,
            color = languageCheckColor,
            modifier = Modifier.size(16.dp)
        )
    }
}

/**
 * SELECT LANGUAGE BUTTON
 */
@Composable
private fun SelectButton(
    state: State,
    listener: Listener?
) {
    AppButton(
        text = "Select",
        state = state.selectButtonState,
        onClick = { listener?.onConfirmSelectedLanguage() },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(48.dp)
    )
}
