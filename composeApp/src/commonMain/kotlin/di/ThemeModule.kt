package di

import Platform.*
import base.theme.shape.AppShapes
import base.theme.shape.DesktopShapes
import base.theme.shape.MobileShapes
import base.theme.shape.WebShapes
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleTheme = DI.Module("ThemeModule") {
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
}