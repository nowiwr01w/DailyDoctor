import model.color.data.background.AppBackgroundColors
import model.color.data.background.classic.ClassicDarkBackgroundColors
import model.color.data.background.classic.ClassicLightBackgroundColors
import core.model.theme.AppTheme.DARK
import core.model.theme.AppTheme.LIGHT
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Platform
import platform.Platform.*
import theme.shape.AppShapes
import theme.shape.platform.DesktopShapes
import theme.shape.platform.MobileShapes
import theme.shape.platform.WebShapes
import theme.typography.AppTypography
import theme.typography.platform.DesktopTypography
import theme.typography.platform.MobileTypography
import theme.typography.platform.WebTypography

val moduleAppPresentationTheme = module {
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