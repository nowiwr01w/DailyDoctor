package app

import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfig
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import logMessage
import manager.brand_config.AppBrandConfigManager
import manager.language.AppLanguageManager
import model.color.allAppColorThemes
import model.color.classic.AppClassicColorThemeLight
import pro.respawn.flowmvi.api.PipelineContext
import usecase.subscription.AppGetSubscriptionPlansUseCase
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class AppViewModel(
    private val appBrandConfigManager: AppBrandConfigManager,
    private val appLanguageManager: AppLanguageManager,
    private val getSubscriptionPlansUseCase: AppGetSubscriptionPlansUseCase
): BaseViewModel<State, Event, Effect>(initialValue = State()) {
    /**
     * INIT
     */
    override suspend fun Ctx.init() {
        initAppLanguage()
        loadBrandConfig {
            loadSubscriptionPlans()
        }
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
    private fun Ctx.loadBrandConfig(afterLoadedCallback: () -> Unit) = io {
        runCatchingApp {
            appBrandConfigManager.getBrandConfig(fromRemote = true)
        }.onSuccess { brandConfig ->
            subscribeOnConfigChanges(brandConfig)
            afterLoadedCallback()
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

    /**
     * SUBSCRIPTION PLANS
     */
    private fun loadSubscriptionPlans() = io { // TODO: Move all this shit to InitAppDataUseCase
        runCatchingApp {
            getSubscriptionPlansUseCase.execute()
        }.onSuccess {
            logMessage("success load plans, size = ${it.size}")
        }.onFailure {
            logMessage("error load plans = ${it.message}")
        }
    }
}
