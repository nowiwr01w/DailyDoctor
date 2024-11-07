package nowiwr01p.daily.doctor.server.domain.usecase.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel
import com.nowiwr01p.model.usecase.UseCase

interface ServerGetOnboardingDataUseCase: UseCase<BrandConfigType, List<OnboardingItemModel>>