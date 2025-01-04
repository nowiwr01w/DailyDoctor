package nowiwr01p.daily.doctor.local_database.di

import nowiwr01p.daily.doctor.local_database.SettingsType
import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import nowiwr01p.daily.doctor.local_database.domain.repository.language.AppLanguageRepositoryPrefs
import nowiwr01p.daily.doctor.local_database.data.repository.language.AppLanguageRepositoryPrefsImpl
import nowiwr01p.daily.doctor.local_database.domain.repository.user.LocalUserRepository
import nowiwr01p.daily.doctor.local_database.data.repository.user.LocalUserRepositoryImpl
import nowiwr01p.daily.doctor.local_database.data.usecase.language.AppPrefsGetLanguageUseCaseImpl
import nowiwr01p.daily.doctor.local_database.data.usecase.language.AppPrefsSetLanguageUseCaseImpl
import nowiwr01p.daily.doctor.local_database.domain.usecase.user.GetLocalUserUseCase
import nowiwr01p.daily.doctor.local_database.data.usecase.user.GetLocalUserUseCaseImpl
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsGetLanguageUseCase
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsSetLanguageUseCase

expect fun getLocalDatabaseModule(): Module

@OptIn(ExperimentalSettingsApi::class)
internal val moduleLocalDatabaseData = module {
    /**
     * USER
     */
    factory<LocalUserRepository> {
        LocalUserRepositoryImpl(settings = get(named(SettingsType.USER.fileName)))
    }
    factory<GetLocalUserUseCase> {
        GetLocalUserUseCaseImpl(get())
    }
    /**
     * LANGUAGE
     */
    factory<AppLanguageRepositoryPrefs> {
        AppLanguageRepositoryPrefsImpl(settings = get(named(SettingsType.LANGUAGE.fileName)))
    }
    factory<AppPrefsGetLanguageUseCase> {
        AppPrefsGetLanguageUseCaseImpl(repository = get<AppLanguageRepositoryPrefs>())
    }
    factory<AppPrefsSetLanguageUseCase> {
        AppPrefsSetLanguageUseCaseImpl(repository = get<AppLanguageRepositoryPrefs>())
    }
}