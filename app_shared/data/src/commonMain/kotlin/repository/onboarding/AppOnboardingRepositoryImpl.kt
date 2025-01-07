package repository.onboarding

import api.onboarding.OnboardingApi
import repository.AppBaseRepository

class AppOnboardingRepositoryImpl(
    private val api: OnboardingApi
): AppBaseRepository(), AppOnboardingRepository {

    override suspend fun getOnboardingData() = io {
        api.getOnboardingData(language = getAppLanguage())
    }
}