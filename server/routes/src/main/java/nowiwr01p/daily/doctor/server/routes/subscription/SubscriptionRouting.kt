package nowiwr01p.daily.doctor.server.routes.subscription

import com.nowiwr01p.model.api.route.SubscriptionRoutes.GetSubscriptionPlansRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.appLanguages
import nowiwr01p.daily.doctor.server.domain.usecase.subscription.ServerGetSubscriptionPlansUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class SubscriptionRouting(
    private val getSubscriptionPlansUseCase: ServerGetSubscriptionPlansUseCase
): BaseRouting() {
    /**
     * PLANS
     */
    fun getSubscriptionPlans(route: Route) = route.get(GetSubscriptionPlansRoute.route) {
        val brand = getParameter<BrandConfigType>( // TODO: Move to BaseRouting
            name = "type",
            paramAsType = { stringParam ->
                BrandConfigType.entries.find { stringParam == it.type }
            }
        ) ?: return@get
        val language = getParameter<Language>( // TODO: Move to BaseRouting
            name = "lang",
            paramAsType = { stringParam ->
                appLanguages.find { it.code == stringParam }
            }
        ) ?: return@get
        runCatchingApp {
            val params = ServerGetSubscriptionPlansUseCase.Params(brand = brand, language = language)
            getSubscriptionPlansUseCase.execute(params)
        }.onSuccess { plans ->
            respondWithSuccessModel(plans)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}