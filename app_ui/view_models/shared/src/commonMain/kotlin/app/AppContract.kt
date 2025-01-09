package app

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import model.color.AppColorTheme
import model.color.classic.AppClassicColorThemeLight
import com.nowiwr01p.model.resources.language.Language
import helpers.snack_bar.data.SnackBarParams

sealed interface Event: BaseEvent

data class State(
    val snackBarParams: SnackBarParams? = null,
    val appLanguage: Language = Language.Georgian,
    val appColorTheme: AppColorTheme = AppClassicColorThemeLight()
): BaseState

sealed interface Effect: BaseEffect
