package data.di

import data.repository.auth.AuthRepositoryImpl
import data.repository.auth.ValidateAuthDataRepositoryImpl
import data.repository.brand.AppBrandRepositoryImpl
import data.repository.theme.AppThemeRepositoryImpl
import data.usecase.brand.GetAppBrandUseCaseImpl
import data.usecase.theme.GetAppThemeModeUseCaseImpl
import data.usecase.theme.SetAppThemeModeUseCaseImpl
import domain.coroutines.dispatchers.AppDispatchers
import domain.repository.auth.AuthRepository
import domain.repository.auth.ValidateAuthDataRepository
import domain.repository.brand.AppBrandRepository
import domain.repository.theme.AppThemeRepository
import domain.usecase.brand.GetAppBrandUseCase
import domain.usecase.theme.GetAppThemeModeUseCase
import domain.usecase.theme.SetAppThemeModeUseCase
import domain.validators.EmailValidator
import domain.validators.PasswordValidator
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val moduleData = DI.Module("DataModule") {
    /**
     * THEME
     */
    bindSingleton<AppThemeRepository> {
        AppThemeRepositoryImpl()
    }
    bindProvider<GetAppThemeModeUseCase> {
        GetAppThemeModeUseCaseImpl(repository = instance<AppThemeRepository>())
    }
    bindProvider<SetAppThemeModeUseCase> {
        SetAppThemeModeUseCaseImpl(repository = instance<AppThemeRepository>())
    }
    /**
     * BRAND
     */
    bindProvider<AppBrandRepository> {
        AppBrandRepositoryImpl()
    }
    bindProvider<GetAppBrandUseCase> {
        GetAppBrandUseCaseImpl(repository = instance<AppBrandRepository>())
    }
    /**
     * AUTH
     */
    bindProvider<ValidateAuthDataRepository> {
        ValidateAuthDataRepositoryImpl(
            dispatchers = instance<AppDispatchers>(),
            emailValidator = instance<EmailValidator>(),
            passwordValidator = instance<PasswordValidator>()
        )
    }
    bindProvider<AuthRepository> {
        AuthRepositoryImpl(dispatchers = instance<AppDispatchers>())
    }
}