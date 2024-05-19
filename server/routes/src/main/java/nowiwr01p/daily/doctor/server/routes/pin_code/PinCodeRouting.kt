package nowiwr01p.daily.doctor.server.routes.pin_code

import com.nowiwr01p.model.api.route.PinCodeRoutes.CreatePinRoute
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class PinCodeRouting: BaseRouting() {

    fun createPin(route: Route) = route.post(CreatePinRoute.route) {

    }
}