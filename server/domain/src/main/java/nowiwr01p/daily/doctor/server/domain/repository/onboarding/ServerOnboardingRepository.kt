package nowiwr01p.daily.doctor.server.domain.repository.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

interface ServerOnboardingRepository {
    suspend fun getOnboardingData(type: BrandConfigType): List<OnboardingItemModel>
}