package nowiwr01p.daily.doctor.server.data.usecase.subscription

import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.server.domain.repository.subscription.ServerSubscriptionRepository
import nowiwr01p.daily.doctor.server.domain.usecase.subscription.ServerGetSubscriptionPlansUseCase

class ServerGetSubscriptionPlansUseCaseImpl(
    private val repository: ServerSubscriptionRepository
): ServerGetSubscriptionPlansUseCase {
    /**
     * PLANS
     */
    override suspend fun execute(input: Language) = repository.getSubscriptionPlans(input)
}