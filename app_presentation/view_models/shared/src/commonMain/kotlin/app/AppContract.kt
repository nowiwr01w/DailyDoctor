package app

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import model.color.AppColorTheme
import model.color.classic.AppClassicColorThemeLight
import nowiwr01p.daily.doctor.new_resources.language.Language

sealed interface Event: BaseEvent

data class State(
    val appLanguage: Language = Language.Georgian,
    val appColorTheme: AppColorTheme = AppClassicColorThemeLight()
): BaseState

sealed interface Effect: BaseEffect
