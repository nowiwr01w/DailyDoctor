package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation

import navigation.AppNavigator
import navigation.navigators.dialogs.DialogsNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.bottom_sheets.MobileBottomSheetsNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.MobileScreensNavigator

interface MobileNavigator: AppNavigator {
    val screensNavigator: MobileScreensNavigator
    val dialogsNavigator: DialogsNavigator
    val bottomSheetsNavigator: MobileBottomSheetsNavigator
}
