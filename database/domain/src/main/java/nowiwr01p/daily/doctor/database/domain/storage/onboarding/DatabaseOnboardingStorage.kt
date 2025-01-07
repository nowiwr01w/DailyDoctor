package nowiwr01p.daily.doctor.database.domain.storage.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.resources.language.Language

interface DatabaseOnboardingStorage {
    suspend fun getOnboardingData(language: Language): List<OnboardingItem>
}