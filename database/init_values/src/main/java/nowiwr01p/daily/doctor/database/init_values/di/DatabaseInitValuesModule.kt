package nowiwr01p.daily.doctor.database.init_values.di

import nowiwr01p.daily.doctor.database.init_values.data.brand.DatabaseInitBrandsTableUseCaseImpl
import nowiwr01p.daily.doctor.database.init_values.data.onboarding.DatabaseInitOnboardingTableUseCaseImpl
import nowiwr01p.daily.doctor.database.init_values.data.onboarding.onboardings.DatabaseInitCallDoctorOnboardingDataUseCaseImpl
import nowiwr01p.daily.doctor.database.init_values.domain.brand.DatabaseInitBrandsTableUseCase
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.DatabaseInitOnboardingTableUseCase
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.onboardings.DatabaseInitCallDoctorOnboardingDataUseCase
import org.koin.dsl.module

val moduleDatabaseInitValues = module {
    /**
     * BRAND
     */
    factory<DatabaseInitBrandsTableUseCase> {
        DatabaseInitBrandsTableUseCaseImpl()
    }
    /**
     * ONBOARDING
     */
    factory<DatabaseInitOnboardingTableUseCase> {
        DatabaseInitOnboardingTableUseCaseImpl(
            initCallDoctorOnboardingDataUseCase = get<DatabaseInitCallDoctorOnboardingDataUseCase>()
        )
    }
    factory<DatabaseInitCallDoctorOnboardingDataUseCase> {
        DatabaseInitCallDoctorOnboardingDataUseCaseImpl()
    }
}