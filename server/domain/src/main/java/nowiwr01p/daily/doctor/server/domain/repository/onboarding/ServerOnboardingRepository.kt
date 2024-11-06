package nowiwr01p.daily.doctor.server.domain.repository.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

interface ServerOnboardingRepository {
    suspend fun getOnboardingData(): List<OnboardingItemModel>
}