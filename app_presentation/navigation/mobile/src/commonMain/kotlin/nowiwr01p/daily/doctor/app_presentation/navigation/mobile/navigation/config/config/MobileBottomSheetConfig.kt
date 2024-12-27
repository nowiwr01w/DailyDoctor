package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config

import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileBottomSheetChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileBottomSheetChild.TestChild

@Serializable
sealed class MobileBottomSheetConfig(val child: MobileBottomSheetChild): BaseNavigationConfig {
    @Serializable
    data object Test: MobileBottomSheetConfig(child = TestChild)
}
