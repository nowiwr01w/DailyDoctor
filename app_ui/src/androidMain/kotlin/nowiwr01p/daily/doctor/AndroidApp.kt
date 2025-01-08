package nowiwr01p.daily.doctor

import android.app.Application
import com.nowiwr01p.model.di.initKoin
import di.appModules
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class AndroidApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setupLogger()
        setupKoin()
    }

    private fun setupLogger() {
        Napier.base(DebugAntilog())
    }

    private fun setupKoin() {
        initKoin(appModules) {
            androidContext(this@AndroidApp)
            androidLogger(level = Level.DEBUG) // TODO: Change only if it's not a Prod variant
        }
    }
}