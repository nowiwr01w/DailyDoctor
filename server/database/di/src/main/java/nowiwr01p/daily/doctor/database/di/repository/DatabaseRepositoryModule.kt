package nowiwr01p.daily.doctor.database.di.repository

import nowiwr01p.daily.doctor.database.data.repository.auth.DatabaseAuthRepositoryImpl
import nowiwr01p.daily.doctor.database.data.repository.brand_config.DatabaseBrandConfigRepositoryImpl
import nowiwr01p.daily.doctor.database.data.repository.onboarding.DatabaseOnboardingRepositoryImpl
import nowiwr01p.daily.doctor.database.data.repository.pin.DatabasePinCodeRepositoryImpl
import nowiwr01p.daily.doctor.database.data.repository.subscription.DatabaseSubscriptionRepositoryImpl
import nowiwr01p.daily.doctor.database.data.repository.verification.DatabaseVerificationRepositoryImpl
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.repository.brand_config.DatabaseBrandConfigRepository
import nowiwr01p.daily.doctor.database.domain.repository.onboarding.DatabaseOnboardingRepository
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.database.domain.repository.subscription.DatabaseSubscriptionRepository
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.database.domain.storage.brand_config.DatabaseBrandConfigStorage
import nowiwr01p.daily.doctor.database.domain.storage.onboarding.DatabaseOnboardingStorage
import nowiwr01p.daily.doctor.database.domain.storage.pin.DatabasePinCodeStorage
import nowiwr01p.daily.doctor.database.domain.storage.subscription.DatabaseSubscriptionStorage
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.domain.storage.verification.DatabaseVerificationStorage
import org.koin.dsl.module

internal val moduleDatabaseRepository = module {
    /**
     * BRAND CONFIG
     */
    factory<DatabaseBrandConfigRepository> {
        DatabaseBrandConfigRepositoryImpl(storage = get<DatabaseBrandConfigStorage>())
    }
    /**
     * ONBOARDING
     */
    factory<DatabaseOnboardingRepository> {
        DatabaseOnboardingRepositoryImpl(storage = get<DatabaseOnboardingStorage>())
    }
    /**
     * AUTH
     */
    factory<DatabaseAuthRepository> {
        DatabaseAuthRepositoryImpl(userStorage = get<DatabaseUserStorage>())
    }
    /**
     * VERIFICATION
     */
    factory<DatabaseVerificationRepository> {
        DatabaseVerificationRepositoryImpl(
            userStorage = get<DatabaseUserStorage>(),
            verificationStorage = get<DatabaseVerificationStorage>()
        )
    }
    /**
     * PIN
     */
    factory<DatabasePinCodeRepository> {
        DatabasePinCodeRepositoryImpl(
            storage = get<DatabasePinCodeStorage>()
        )
    }
    /**
     * SUBSCRIPTION
     */
    factory<DatabaseSubscriptionRepository> {
        DatabaseSubscriptionRepositoryImpl(
            storage = get<DatabaseSubscriptionStorage>()
        )
    }
}