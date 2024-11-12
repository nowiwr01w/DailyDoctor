package repository.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem

interface AppOnboardingRepository {
    suspend fun getOnboardingData(): List<OnboardingItem>
}