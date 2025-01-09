package manager.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import kotlinx.coroutines.flow.Flow

interface AppOnboardingManager {
    suspend fun getOnboardingData(fromRemote: Boolean): Flow<List<OnboardingItem>>
}