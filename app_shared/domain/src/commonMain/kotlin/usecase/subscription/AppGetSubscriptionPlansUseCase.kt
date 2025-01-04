package usecase.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.usecase.UseCase

interface AppGetSubscriptionPlansUseCase: UseCase<Unit, List<SubscriptionPlan>>