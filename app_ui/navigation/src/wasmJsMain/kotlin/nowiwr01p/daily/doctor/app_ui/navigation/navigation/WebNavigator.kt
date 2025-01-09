package nowiwr01p.daily.doctor.app_ui.navigation.navigation

import navigation.AppNavigator
import navigation.navigators.dialogs.DialogsNavigator
import navigation.navigators.screens.ScreensNavigator

interface WebNavigator: AppNavigator {
    val screensNavigator: ScreensNavigator
    val dialogsNavigator: DialogsNavigator
}
