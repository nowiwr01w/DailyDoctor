package api.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem

interface OnboardingApi {
    suspend fun getOnboardingData(): List<OnboardingItem>
}