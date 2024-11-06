package manager.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemModel
import kotlinx.coroutines.flow.Flow

interface AppOnboardingManager {
    suspend fun getOnboardingData(fromRemote: Boolean): Flow<List<OnboardingItemModel>>
}