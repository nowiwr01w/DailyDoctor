package nowiwr01p.daily.doctor.server.routes.pin_code

import com.nowiwr01p.model.api.route.PinCodeRoutes.ChangePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CheckPinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CreatePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.DeletePinRoute
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerChangePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCheckPinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCreatePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerDeletePinCodeUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class PinCodeRouting(
    private val changePinCodeUseCase: ServerChangePinCodeUseCase,
    private val createPinCodeUseCase: ServerCreatePinCodeUseCase,
    private val deletePinCodeUseCase: ServerDeletePinCodeUseCase,
    private val checkPinCodeUseCase: ServerCheckPinCodeUseCase
): BaseRouting() {

    fun createPinCode(route: Route) = route.post(CreatePinRoute.route) {

    }

    fun checkPinCode(route: Route) = route.post(CheckPinRoute.route) {

    }

    fun changePinCode(route: Route) = route.post(ChangePinRoute.route) {

    }

    fun deletePinCode(route: Route) = route.post(DeletePinRoute.route) {

    }
}