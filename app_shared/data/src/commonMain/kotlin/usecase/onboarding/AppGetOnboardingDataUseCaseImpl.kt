package usecase.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import repository.onboarding.AppOnboardingRepository

class AppGetOnboardingDataUseCaseImpl(
    private val repository: AppOnboardingRepository
): AppGetOnboardingDataUseCase {

    override suspend fun execute(input: Unit): List<OnboardingItem> {
        return repository.getOnboardingData()
    }
}