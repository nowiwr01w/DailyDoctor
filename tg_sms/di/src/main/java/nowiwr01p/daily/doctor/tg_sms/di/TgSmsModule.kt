package nowiwr01p.daily.doctor.tg_sms.di

import nowiwr01p.daily.doctor.base_api_client.di.moduleBaseApiClient
import nowiwr01p.daily.doctor.tg_sms.data.api.TgSmsApiImpl
import nowiwr01p.daily.doctor.tg_sms.data.repository.TgSmsRepositoryImpl
import nowiwr01p.daily.doctor.tg_sms.data.usecase.TgSendVerificationCodeUseCaseImpl
import nowiwr01p.daily.doctor.tg_sms.domain.api.TgSmsApi
import nowiwr01p.daily.doctor.tg_sms.domain.repository.TgSmsRepository
import nowiwr01p.daily.doctor.tg_sms.domain.usecase.TgSendVerificationCodeUseCase
import org.koin.dsl.module
import java.io.FileInputStream
import java.util.Properties

private val moduleTelegramSms = module {
    /**
     * API
     */
    single<TgSmsApi> {
        TgSmsApiImpl(
            apiKey = Properties()
                .apply { load(FileInputStream("tg_sms_config.properties")) }
                .getProperty("tg_sms.api.key")
        )
    }
    /**
     * REPOSITORY
     */
    factory<TgSmsRepository> {
        TgSmsRepositoryImpl(api = get<TgSmsApi>())
    }
    /**
     * USE CASES
     */
    factory<TgSendVerificationCodeUseCase> {
        TgSendVerificationCodeUseCaseImpl(repository = get<TgSmsRepository>())
    }
}

val tgSmsModules = listOf(
    moduleTelegramSms,
    moduleBaseApiClient
)