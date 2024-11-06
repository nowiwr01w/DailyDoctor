package manager.onboarding

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import usecase.onboarding.AppGetOnboardingDataUseCase

class AppOnboardingManagerImpl(
    private val appScope: AppScope,
    private val appGetOnboardingDataUseCase: AppGetOnboardingDataUseCase
): AppOnboardingManager {

    private val onboardingData = MutableStateFlow<List<OnboardingItemModel>>(listOf())

    override suspend fun getOnboardingData(fromRemote: Boolean): Flow<List<OnboardingItemModel>> {
        if (fromRemote) {
            loadOnboardingData()
        }
        return onboardingData
    }

    private fun loadOnboardingData() = appScope.scope.launch {
        runCatchingApp {
            appGetOnboardingDataUseCase.execute()
        }.onSuccess { data ->
            onboardingData.emit(data)
        }
    }
}