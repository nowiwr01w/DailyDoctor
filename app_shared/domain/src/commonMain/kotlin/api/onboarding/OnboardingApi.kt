package api.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

interface OnboardingApi {
    suspend fun getOnboardingData(): List<OnboardingItemModel>
}