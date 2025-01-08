package subscription.data

sealed interface SubscriptionPeriod {
    data object Monthly: SubscriptionPeriod
    data object Yearly: SubscriptionPeriod
}