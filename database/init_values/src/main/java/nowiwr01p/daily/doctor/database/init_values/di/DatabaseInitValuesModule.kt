package nowiwr01p.daily.doctor.database.init_values.di

import nowiwr01p.daily.doctor.database.init_values.data.onboarding.DatabaseInitOnboardingTableUseCaseImpl
import nowiwr01p.daily.doctor.database.init_values.data.subscription.DatabaseInitSubscriptionPlansTableUseCaseImpl
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.DatabaseInitOnboardingTableUseCase
import nowiwr01p.daily.doctor.database.init_values.domain.subscription.DatabaseInitSubscriptionPlansTableUseCase
import org.koin.dsl.module

val moduleDatabaseInitValues = module {
    /**
     * ONBOARDING
     */
    factory<DatabaseInitOnboardingTableUseCase> {
        DatabaseInitOnboardingTableUseCaseImpl()
    }
    /**
     * SUBSCRIPTION
     */
    factory<DatabaseInitSubscriptionPlansTableUseCase> {
        DatabaseInitSubscriptionPlansTableUseCaseImpl()
    }
}