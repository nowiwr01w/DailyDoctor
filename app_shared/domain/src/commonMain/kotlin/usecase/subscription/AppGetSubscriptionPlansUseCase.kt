package usecase.subscription

import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlan
import com.nowiwr01p.model.usecase.UseCase

interface AppGetSubscriptionPlansUseCase: UseCase<Unit, List<SubscriptionPlan>>