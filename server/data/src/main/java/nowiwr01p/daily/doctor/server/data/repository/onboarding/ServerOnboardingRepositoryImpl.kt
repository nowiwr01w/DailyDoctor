package nowiwr01p.daily.doctor.server.data.repository.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel
import nowiwr01p.daily.doctor.database.domain.repository.onboarding.DatabaseOnboardingRepository
import nowiwr01p.daily.doctor.server.domain.repository.onboarding.ServerOnboardingRepository

class ServerOnboardingRepositoryImpl(
    private val repository: DatabaseOnboardingRepository
): ServerOnboardingRepository {

    override suspend fun getOnboardingData(type: BrandConfigType): List<OnboardingItemModel> {
        return repository.getOnboardingData(type)
    }
}