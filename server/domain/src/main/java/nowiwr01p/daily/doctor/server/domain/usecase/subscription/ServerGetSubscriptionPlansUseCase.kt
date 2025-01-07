package nowiwr01p.daily.doctor.server.domain.usecase.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.usecase.UseCase

interface ServerGetSubscriptionPlansUseCase: UseCase<Language, List<SubscriptionPlan>>