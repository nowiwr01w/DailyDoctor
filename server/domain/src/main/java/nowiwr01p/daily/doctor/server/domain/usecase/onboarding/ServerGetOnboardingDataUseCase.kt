package nowiwr01p.daily.doctor.server.domain.usecase.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.usecase.UseCase

interface ServerGetOnboardingDataUseCase: UseCase<Language, List<OnboardingItem>>