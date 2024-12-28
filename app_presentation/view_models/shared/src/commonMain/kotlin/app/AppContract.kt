package app

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import model.color.AppColorTheme
import model.color.classic.AppClassicColorThemeLight

sealed interface Event: BaseEvent

data class State(
    val appColorTheme: AppColorTheme = AppClassicColorThemeLight()
): BaseState

sealed interface Effect: BaseEffect
