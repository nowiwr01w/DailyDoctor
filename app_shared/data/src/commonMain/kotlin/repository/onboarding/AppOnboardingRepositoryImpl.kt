package repository.onboarding

import api.onboarding.OnboardingApi
import com.nowiwr01p.model.repository.BaseRepository

class AppOnboardingRepositoryImpl(
    private val api: OnboardingApi
): BaseRepository(), AppOnboardingRepository {

    override suspend fun getOnboardingData() = io {
        api.getOnboardingData()
    }
}