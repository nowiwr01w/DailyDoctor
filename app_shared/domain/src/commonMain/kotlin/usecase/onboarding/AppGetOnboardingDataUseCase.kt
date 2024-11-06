package usecase.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemModel
import com.nowiwr01p.model.usecase.UseCase

interface AppGetOnboardingDataUseCase: UseCase<Unit, List<OnboardingItemModel>>