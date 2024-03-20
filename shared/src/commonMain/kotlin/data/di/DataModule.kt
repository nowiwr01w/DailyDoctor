package data.di

import data.dispatchers.AppDispatchersImpl
import data.repository.brand.AppBrandRepositoryImpl
import data.repository.theme.AppThemeRepositoryImpl
import data.usecase.brand.GetAppBrandUseCaseImpl
import data.usecase.theme.GetAppThemeModeUseCaseImpl
import data.usecase.theme.SetAppThemeModeUseCaseImpl
import domain.dispatchers.AppDispatchers
import domain.repository.brand.AppBrandRepository
import domain.repository.theme.AppThemeRepository
import domain.usecase.brand.GetAppBrandUseCase
import domain.usecase.theme.GetAppThemeModeUseCase
import domain.usecase.theme.SetAppThemeModeUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val moduleData = DI.Module("DataModule") {
    /**
     * DISPATCHERS
     */
    bindSingleton<AppDispatchers> {
        AppDispatchersImpl()
    }
    /**
     * THEME
     */
    bindSingleton<AppThemeRepository> {
        AppThemeRepositoryImpl()
    }
    bindProvider<GetAppThemeModeUseCase> {
        GetAppThemeModeUseCaseImpl(instance())
    }
    bindProvider<SetAppThemeModeUseCase> {
        SetAppThemeModeUseCaseImpl(instance())
    }
    /**
     * BRAND
     */
    bindProvider<AppBrandRepository> {
        AppBrandRepositoryImpl()
    }
    bindProvider<GetAppBrandUseCase> {
        GetAppBrandUseCaseImpl(instance())
    }
}