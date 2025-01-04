package usecase.subscription

import repository.subscription.AppSubscriptionRepository

class AppGetSubscriptionPlansUseCaseImpl(
    private val repository: AppSubscriptionRepository
): AppGetSubscriptionPlansUseCase {
    /**
     * PLANS
     */
    override suspend fun execute(input: Unit) = repository.loadSubscriptionPlans()
}