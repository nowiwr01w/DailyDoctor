package nowiwr01p.daily.doctor.server.main.di

import com.nowiwr01p.model.di.moduleBaseCoroutines
import org.kodein.di.DI

private val allServerModules = listOf(
    moduleBaseCoroutines
)

val serverModules = DI {
    importAll(
        modules = allServerModules,
        allowOverride = true
    )
}