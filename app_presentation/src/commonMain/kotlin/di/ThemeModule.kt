package di

import Platform.*
import domain.model.color.data.background.AppBackgroundColors
import domain.model.color.data.background.classic.ClassicDarkBackgroundColors
import domain.model.color.data.background.classic.ClassicLightBackgroundColors
import base.theme.shape.AppShapes
import base.theme.shape.DesktopShapes
import base.theme.shape.MobileShapes
import base.theme.shape.WebShapes
import base.theme.typography.AppTypography
import base.theme.typography.DesktopTypography
import base.theme.typography.MobileTypography
import base.theme.typography.WebTypography
import domain.repository.theme.AppTheme.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

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