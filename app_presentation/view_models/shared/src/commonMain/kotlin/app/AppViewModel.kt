package app

import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import manager.brand_config.AppBrandConfigManager
import manager.language.AppLanguageManager
import model.color.allAppColorThemes
import model.color.classic.AppClassicColorThemeLight
import pro.respawn.flowmvi.api.PipelineContext
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class AppViewModel(
    private val appBrandConfigManager: AppBrandConfigManager,
    private val appLanguageManager: AppLanguageManager
): BaseViewModel<State, Event, Effect>(initialValue = State()) {
    /**
     * INIT
     */
    override suspend fun Ctx.init() {
        initAppLanguage()
        loadBrandConfig()
    }

    /**
     * APP LANGUAGE
     */
    private fun Ctx.initAppLanguage() = io {
        appLanguageManager.getAppLanguagesData().collect { data ->
            setState { copy(appLanguage = data.selectedLanguage) }
        }
    }

    /**
     * BRAND CONFIG
     */
    private fun Ctx.loadBrandConfig() = io {
        runCatchingApp {
            appBrandConfigManager.getBrandConfig(fromRemote = true)
        }.onSuccess { brandConfig ->
            subscribeOnConfigChanges(brandConfig)
        }.onFailure {
            // TODO: Show [App temporary sucks] dialog stub
        }
    }

    private fun Ctx.subscribeOnConfigChanges(brandConfig: Flow<BrandConfig>) = io {
        brandConfig.collectLatest { config ->
            initAppTheme(config)
        }
    }

    /**
     * APP THEME
     */
    private suspend fun Ctx.initAppTheme(config: BrandConfig) = config.brandSettings
        .availableColorThemes
        .firstOrNull()
        ?.let { appColorThemeType ->
            val appColorTheme = allAppColorThemes.find { it.type == appColorThemeType }
            val appColorThemeNotNull = appColorTheme ?: AppClassicColorThemeLight()
            setState { copy(appColorTheme = appColorThemeNotNull) }
        }
}
