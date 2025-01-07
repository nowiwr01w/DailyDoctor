package nowiwr01p.daily.doctor.server.data.repository.onboarding

import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.database.domain.repository.onboarding.DatabaseOnboardingRepository
import nowiwr01p.daily.doctor.server.domain.repository.onboarding.ServerOnboardingRepository

class ServerOnboardingRepositoryImpl(
    private val repository: DatabaseOnboardingRepository
): ServerOnboardingRepository {
    override suspend fun getOnboardingData(language: Language) = repository.getOnboardingData(language)
}