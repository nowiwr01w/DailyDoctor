import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import user.repository.LocalUserRepository
import user.repository.LocalUserRepositoryImpl
import user.usecase.GetLocalUserUseCase
import user.usecase.GetLocalUserUseCaseImpl

expect fun getLocalDatabaseModule(): Module

@OptIn(ExperimentalSettingsApi::class)
val moduleLocalDatabaseData = module {
    /**
     * USER
     */
    factory<LocalUserRepository> {
        LocalUserRepositoryImpl(settings = get(named(SettingsType.USER.fileName)))
    }
    factory<GetLocalUserUseCase> {
        GetLocalUserUseCaseImpl(get())
    }
}