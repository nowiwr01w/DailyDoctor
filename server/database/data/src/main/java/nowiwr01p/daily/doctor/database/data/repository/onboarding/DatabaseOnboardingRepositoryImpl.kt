package nowiwr01p.daily.doctor.database.data.repository.onboarding

import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.database.domain.repository.onboarding.DatabaseOnboardingRepository
import nowiwr01p.daily.doctor.database.domain.storage.onboarding.DatabaseOnboardingStorage

class DatabaseOnboardingRepositoryImpl(
    private val storage: DatabaseOnboardingStorage
): DatabaseOnboardingRepository {
    override suspend fun getOnboardingData(language: Language) = storage.getOnboardingData(language)
}