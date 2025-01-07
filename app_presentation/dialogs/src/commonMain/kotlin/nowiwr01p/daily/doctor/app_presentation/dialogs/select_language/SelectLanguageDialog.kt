package nowiwr01p.daily.doctor.app_presentation.dialogs.select_language

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nowiwr01p.model.resources.component_with_resources.dialogs.select_language.SelectLanguageDialogResources
import com.nowiwr01p.model.resources.component_with_resources.dialogs.select_language.SelectLanguageResources
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian
import components.button.AppButton
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.image.AppImage
import extensions.advancedShadow
import navigation.config.child.DialogsChild.SelectLanguageChild
import navigation.screen_results.ScreenResultKey.SelectLanguageResultKey
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Effect.CloseDialog
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Event.OnConfirmSelectedLanguage
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Event.OnSelectLanguageClicked
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_flag_georgia
import nowiwr01p.daily.doctor.resources.ic_flag_russia
import nowiwr01p.daily.doctor.resources.ic_flag_ukraine
import nowiwr01p.daily.doctor.resources.ic_flag_us
import theme.CustomTheme.colors
import theme.CustomTheme.shapes
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
    SelectLanguageDialogResources(language = state.selectedLanguage) { resources ->
        resources.Content(
            state = state,
            listener = listener
        )
    }
}

@Composable
private fun SelectLanguageResources.Content(
    state: State,
    listener: Listener?
) {
    Column(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Title()
        LanguagesList(state, listener)
        SelectButton(listener)
        ChangeInSettingsText()
    }
}

/**
 * TITLE AND CLOSE BUTTON
 */
@Composable
private fun SelectLanguageResources.Title() = Text(
    text = appLanguageTitle,
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
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = false,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(state.allLanguages) { language ->
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
    val image = remember(language) {
        when (language) {
            is Georgian -> Res.drawable.ic_flag_georgia
            is English -> Res.drawable.ic_flag_us
            is Russian -> Res.drawable.ic_flag_russia
            is Ukrainian -> Res.drawable.ic_flag_ukraine
        }
    }
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) colors.backgroundColors.redBackgroundColor else Color.Transparent,
        animationSpec = tween()
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .advancedShadow(
                cornerRadius = 16.dp,
                outsideBlurRadius = 6.dp
            )
            .clip(shapes.large)
            .background(
                color = colors.backgroundColors.whiteBackgroundColor,
                shape = shapes.large
            )
            .border(
                width = 1.5.dp,
                color = borderColor,
                shape = shapes.large
            )
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource(),
                onClick = onSelectLanguageClick
            )
            .padding(vertical = 16.dp)
    ) {
        AppImage(
            image = image,
            modifier = Modifier
                .size(32.dp)
                .clip(shapes.circle)
        )
        Text(
            text = language.name,
            color = colors.textColors.blackTextColor.copy(alpha = 0.75f),
            style = typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/**
 * SELECT LANGUAGE BUTTON
 */
@Composable
private fun SelectLanguageResources.SelectButton(listener: Listener?) = AppButton(
    text = selectButtonText,
    state = DARK_GRAY_ACTIVE,
    onClick = { listener?.onConfirmSelectedLanguage() },
    modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 4.dp)
        .fillMaxWidth()
        .height(48.dp)
)

/**
 * CHANGE LANGUAGE INFO
 */
@Composable
private fun SelectLanguageResources.ChangeInSettingsText() = Text(
    text = changeInSettingsText,
    color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
    style = typography.labelMedium,
    textAlign = TextAlign.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 4.dp, start = 24.dp, end = 24.dp)
)