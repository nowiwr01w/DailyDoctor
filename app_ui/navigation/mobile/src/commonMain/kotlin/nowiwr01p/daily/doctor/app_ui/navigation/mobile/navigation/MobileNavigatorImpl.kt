package nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation

import navigation.navigators.dialogs.DialogsNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.bottom_sheets.MobileBottomSheetsNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.MobileScreensNavigator

class MobileNavigatorImpl(
    override val screensNavigator: MobileScreensNavigator,
    override val dialogsNavigator: DialogsNavigator,
    override val bottomSheetsNavigator: MobileBottomSheetsNavigator
): MobileNavigator
