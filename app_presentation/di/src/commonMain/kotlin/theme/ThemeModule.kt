package di.theme

import core.model.color.data.background.AppBackgroundColors
import core.model.color.data.background.classic.ClassicDarkBackgroundColors
import core.model.color.data.background.classic.ClassicLightBackgroundColors
import core.model.theme.AppTheme.DARK
import core.model.theme.AppTheme.LIGHT
import nowiwr01p.daily.doctor.app_presentation.theme.shape.AppShapes
import nowiwr01p.daily.doctor.app_presentation.theme.shape.platform.DesktopShapes
import nowiwr01p.daily.doctor.app_presentation.theme.shape.platform.MobileShapes
import nowiwr01p.daily.doctor.app_presentation.theme.shape.platform.WebShapes
import nowiwr01p.daily.doctor.app_presentation.theme.typography.AppTypography
import nowiwr01p.daily.doctor.app_presentation.theme.typography.platform.DesktopTypography
import nowiwr01p.daily.doctor.app_presentation.theme.typography.platform.MobileTypography
import nowiwr01p.daily.doctor.app_presentation.theme.typography.platform.WebTypography
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Platform.*

val moduleTheme = module {
    /**
     * SHAPES
     */
    factory<AppShapes>(named(IOS)) {
        MobileShapes()
    }
    factory<AppShapes>(named(ANDROID)) {
        MobileShapes()
    }
    factory<AppShapes>(named(DESKTOP)) {
        DesktopShapes()
    }
    factory<AppShapes>(named(WEB)) {
        WebShapes()
    }
    /**
     * TYPOGRAPHY
     */
    factory<AppTypography>(named(IOS)) {
        MobileTypography()
    }
    factory<AppTypography>(named(ANDROID)) {
        MobileTypography()
    }
    factory<AppTypography>(named(DESKTOP)) {
        DesktopTypography()
    }
    factory<AppTypography>(named(WEB)) {
        WebTypography()
    }
    /**
     * COLORS
     */
    factory<AppBackgroundColors>(named(LIGHT)) {
        ClassicLightBackgroundColors()
    }
    factory<AppBackgroundColors>(named(DARK)) {
        ClassicDarkBackgroundColors()
    }
}