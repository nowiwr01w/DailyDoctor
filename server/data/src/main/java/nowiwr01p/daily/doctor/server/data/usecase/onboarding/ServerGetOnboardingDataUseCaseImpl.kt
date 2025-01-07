package nowiwr01p.daily.doctor.server.data.usecase.onboarding

import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.server.domain.repository.onboarding.ServerOnboardingRepository
import nowiwr01p.daily.doctor.server.domain.usecase.onboarding.ServerGetOnboardingDataUseCase

class ServerGetOnboardingDataUseCaseImpl(
    private val repository: ServerOnboardingRepository
): ServerGetOnboardingDataUseCase {
    override suspend fun execute(input: Language) = repository.getOnboardingData(input)
}