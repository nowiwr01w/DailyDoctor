package data.di

import data.dispatchers.AppDispatchersImpl
import data.repository.AppThemeRepositoryImpl
import data.usecase.GetAppThemeModeUseCaseImpl
import data.usecase.SetAppThemeModeUseCaseImpl
import domain.dispatchers.AppDispatchers
import domain.repository.AppThemeRepository
import domain.usecase.app_theme.GetAppThemeModeUseCase
import domain.usecase.app_theme.SetAppThemeModeUseCase
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
}