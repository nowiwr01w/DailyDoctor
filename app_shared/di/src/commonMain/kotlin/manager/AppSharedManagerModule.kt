package manager

import org.koin.dsl.module
import usecase.brand_config.AppGetBrandConfigUseCase

internal val moduleAppSharedManager = module {
    /**
     * BRAND CONFIG
     */
    single<AppBrandConfigManager> {
        AppBrandConfigManagerImpl(appGetBrandConfigUseCase = get<AppGetBrandConfigUseCase>())
    }
}