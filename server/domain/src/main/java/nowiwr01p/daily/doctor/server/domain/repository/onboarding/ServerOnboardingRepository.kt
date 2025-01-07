package nowiwr01p.daily.doctor.server.domain.repository.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.resources.language.Language

interface ServerOnboardingRepository {
    suspend fun getOnboardingData(language: Language): List<OnboardingItem>
}