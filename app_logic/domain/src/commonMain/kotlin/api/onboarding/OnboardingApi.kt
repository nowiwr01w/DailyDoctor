package api.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.resources.language.Language

interface OnboardingApi {
    suspend fun getOnboardingData(language: Language): List<OnboardingItem>
}