package usecase.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.usecase.UseCase

interface AppGetOnboardingDataUseCase: UseCase<Unit, List<OnboardingItem>>