package nowiwr01p.daily.doctor.database.init_values.data.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.app_config.config.BrandConfigType.*
import com.nowiwr01p.model.usecase.execute
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.DatabaseInitOnboardingTableUseCase
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.onboardings.DatabaseInitCallDoctorOnboardingDataUseCase

class DatabaseInitOnboardingTableUseCaseImpl(
    private val initCallDoctorOnboardingDataUseCase: DatabaseInitCallDoctorOnboardingDataUseCase
): DatabaseInitOnboardingTableUseCase {

    override suspend fun execute(input: Unit) {
        BrandConfigType.entries.forEach { brand ->
            when (brand) {
                CALL_DOCTOR_CONFIG_TYPE -> initCallDoctorOnboardingDataUseCase.execute()
            }
        }
    }
}