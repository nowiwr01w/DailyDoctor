package nowiwr01p.daily.doctor.database.domain.repository.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.resources.language.Language

interface DatabaseOnboardingRepository {
    suspend fun getOnboardingData(language: Language): List<OnboardingItem>
}