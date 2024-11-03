import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Platform.ANDROID
import platform.Platform.DESKTOP
import platform.Platform.IOS
import platform.Platform.WEB
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
}