package app

import app.AppContract.*
import app.AppContract.Event.*
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import manager.brand_config.AppBrandConfigManager
import model.color.allAppColorThemes
import model.color.classic.AppClassicColorThemeLight
import view_model.BaseViewModel

class AppViewModel(
    scope: CoroutineScope,
    private val appBrandConfigManager: AppBrandConfigManager
): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
        }
    }

    private fun init() {
        loadBrandConfig()
    }

    /**
     * BRAND CONFIG
     */
    private fun loadBrandConfig() = hide {
        runCatchingApp {
            appBrandConfigManager.getBrandConfig(fromRemote = true)
        }.onSuccess { brandConfig ->
            subscribeOnConfigChanges(brandConfig)
        }.onFailure {
            // TODO: Show [App temporary sucks] dialog stub
        }
    }

    private fun subscribeOnConfigChanges(brandConfig: Flow<BrandConfig>) = hide {
        brandConfig.collectLatest { config ->
            initAppTheme(config)
        }
    }

    /**
     * APP THEME
     */
    private fun initAppTheme(config: BrandConfig) = config.brandSettings
        .availableColorThemes
        .firstOrNull()
        ?.let { appColorThemeType ->
            val appColorTheme = allAppColorThemes.find { it.type == appColorThemeType }
            val appColorThemeNotNull = appColorTheme ?: AppClassicColorThemeLight()
            setState { copy(appColorTheme = appColorThemeNotNull) }
        }
}