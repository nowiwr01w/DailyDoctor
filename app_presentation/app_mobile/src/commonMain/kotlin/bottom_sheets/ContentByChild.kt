package bottom_sheets

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileBottomSheetChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileBottomSheetChild.TestChild

@Composable
internal fun MobileBottomSheetChild.getBottomSheetContent(navigator: MobileNavigator) = when (this) {
    is TestChild -> TestBottomSheet()
}
