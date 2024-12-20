package nowiwr01p.daily.doctor.app_presentation.navigation.mobile

import navigation.AppNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigators.bottom_sheets.MobileBottomSheetsNavigator
import navigation.navigators.dialogs.DialogsNavigator
import navigation.navigators.screens.ScreensNavigator

interface MobileNavigator: AppNavigator {
    val screensNavigator: ScreensNavigator
    val dialogsNavigator: DialogsNavigator
    val bottomSheetsNavigator: MobileBottomSheetsNavigator
}
