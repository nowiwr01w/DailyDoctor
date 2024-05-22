package data.di

import data.repository.auth.AuthRepositoryImpl
import data.repository.auth.ValidateAuthDataRepositoryImpl
import data.repository.brand.AppBrandRepositoryImpl
import data.repository.theme.AppThemeRepositoryImpl
import data.usecase.brand.GetAppBrandUseCaseImpl
import data.usecase.theme.GetAppThemeModeUseCaseImpl
import data.usecase.theme.SetAppThemeModeUseCaseImpl
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import domain.repository.auth.AuthRepository
import domain.repository.auth.ValidateAuthDataRepository
import domain.repository.brand.AppBrandRepository
import domain.repository.theme.AppThemeRepository
import domain.usecase.brand.GetAppBrandUseCase
import domain.usecase.theme.GetAppThemeModeUseCase
import domain.usecase.theme.SetAppThemeModeUseCase
import domain.validators.EmailValidator
import domain.validators.PasswordValidator
import org.koin.dsl.module

val moduleDataApp = module {
    /**
     * THEME
     */
    single<AppThemeRepository> {
        AppThemeRepositoryImpl()
    }
    factory<GetAppThemeModeUseCase> {
        GetAppThemeModeUseCaseImpl(repository = get<AppThemeRepository>())
    }
    factory<SetAppThemeModeUseCase> {
        SetAppThemeModeUseCaseImpl(repository = get<AppThemeRepository>())
    }
    /**
     * BRAND
     */
    factory<AppBrandRepository> {
        AppBrandRepositoryImpl()
    }
    factory<GetAppBrandUseCase> {
        GetAppBrandUseCaseImpl(repository = get<AppBrandRepository>())
    }
    /**
     * AUTH
     */
    factory<ValidateAuthDataRepository> {
        ValidateAuthDataRepositoryImpl(
            dispatchers = get<AppDispatchers>(),
            emailValidator = get<EmailValidator>(),
            passwordValidator = get<PasswordValidator>()
        )
    }
    factory<AuthRepository> {
        AuthRepositoryImpl(dispatchers = get<AppDispatchers>())
    }
}