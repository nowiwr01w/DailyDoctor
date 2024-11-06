package repository.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

interface AppOnboardingRepository {
    suspend fun getOnboardingData(): List<OnboardingItemModel>
}