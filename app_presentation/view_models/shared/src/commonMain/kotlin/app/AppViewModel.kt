package app

import com.nowiwr01p.model.model.app_config.config.BrandConfig
import kotlinx.coroutines.flow.catch
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
        subscribeOnAppConfigChanges()
        subscribeOnAppLanguageChanges()
    }

    /**
     * APP CONFIG
     */
    private fun Ctx.subscribeOnAppConfigChanges() = io {
        appBrandConfigManager.getBrandConfig(fromRemote = false)
            .catch {
                // TODO: Show [App temporary sucks] dialog stub
            }
            .collectLatest { config ->
                initAppTheme(config)
            }
    }

    /**
     * APP LANGUAGE
     */
    private fun Ctx.subscribeOnAppLanguageChanges() = io {
        appLanguageManager.getAppLanguagesData().collect { data ->
            setState { copy(appLanguage = data.selectedLanguage) }
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
