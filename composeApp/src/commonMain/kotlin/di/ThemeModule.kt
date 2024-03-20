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
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleTheme = DI.Module("ThemeModule") {
    /**
     * SHAPES
     */
    bindProvider<AppShapes>(IOS) {
        MobileShapes()
    }
    bindProvider<AppShapes>(ANDROID) {
        MobileShapes()
    }
    bindProvider<AppShapes>(DESKTOP) {
        DesktopShapes()
    }
    bindProvider<AppShapes>(WEB) {
        WebShapes()
    }
    /**
     * TYPOGRAPHY
     */
    bindProvider<AppTypography>(IOS) {
        MobileTypography()
    }
    bindProvider<AppTypography>(ANDROID) {
        MobileTypography()
    }
    bindProvider<AppTypography>(DESKTOP) {
        DesktopTypography()
    }
    bindProvider<AppTypography>(WEB) {
        WebTypography()
    }
    /**
     * COLORS
     */
    bindProvider<AppBackgroundColors>(LIGHT) {
        ClassicLightBackgroundColors()
    }
    bindProvider<AppBackgroundColors>(DARK) {
        ClassicDarkBackgroundColors()
    }
}