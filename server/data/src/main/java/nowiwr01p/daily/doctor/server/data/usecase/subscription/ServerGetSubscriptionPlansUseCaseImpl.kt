package nowiwr01p.daily.doctor.server.data.usecase.subscription

import nowiwr01p.daily.doctor.server.domain.repository.subscription.ServerSubscriptionRepository
import nowiwr01p.daily.doctor.server.domain.usecase.subscription.ServerGetSubscriptionPlansUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.subscription.ServerGetSubscriptionPlansUseCase.Params

class ServerGetSubscriptionPlansUseCaseImpl(
    private val repository: ServerSubscriptionRepository
): ServerGetSubscriptionPlansUseCase {
    /**
     * PLANS
     */
    override suspend fun execute(input: Params) = repository.getSubscriptionPlans(
        brand = input.brand,
        language = input.language
    )
}