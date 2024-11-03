import calldoctor.app_shared.config.data.api.AppConfigApiImpl
import calldoctor.app_shared.config.data.repository.AppConfigRepositoryImpl
import calldoctor.app_shared.config.data.usecase.GetAppConfigUseCaseImpl
import calldoctor.app_shared.config.domain.api.AppConfigApi
import calldoctor.app_shared.config.domain.repository.AppConfigRepository
import calldoctor.app_shared.config.domain.usecase.GetAppConfigUseCase
import org.koin.dsl.module

val moduleAppSharedConfig = module {
    /**
     * API
     */
    factory<AppConfigApi> {
        AppConfigApiImpl()
    }
    /**
     * REPOSITORY
     */
    factory<AppConfigRepository> {
        AppConfigRepositoryImpl(api = get<AppConfigApi>())
    }
    /**
     * USE CASE
     */
    factory<GetAppConfigUseCase> {
        GetAppConfigUseCaseImpl(get())
    }
}