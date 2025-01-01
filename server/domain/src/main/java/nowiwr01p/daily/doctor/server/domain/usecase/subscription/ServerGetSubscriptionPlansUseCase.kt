package nowiwr01p.daily.doctor.server.domain.usecase.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.usecase.UseCase

interface ServerGetSubscriptionPlansUseCase: UseCase<Unit, List<SubscriptionPlan>>