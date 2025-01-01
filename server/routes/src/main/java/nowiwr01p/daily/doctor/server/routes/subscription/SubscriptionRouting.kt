package nowiwr01p.daily.doctor.server.routes.subscription

import com.nowiwr01p.model.api.route.SubscriptionRoutes.GetSubscriptionPlansRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.usecase.execute
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.server.domain.usecase.subscription.ServerGetSubscriptionPlansUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class SubscriptionRouting(
    private val getSubscriptionPlansUseCase: ServerGetSubscriptionPlansUseCase
): BaseRouting() {
    /**
     * PLANS
     */
    fun getSubscriptionPlans(route: Route) = route.get(GetSubscriptionPlansRoute.route) {
        runCatchingApp {
            getSubscriptionPlansUseCase.execute()
        }.onSuccess { plans ->
            respondWithSuccessModel(plans)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}