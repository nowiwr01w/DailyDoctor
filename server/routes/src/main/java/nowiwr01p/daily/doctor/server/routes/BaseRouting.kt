package nowiwr01p.daily.doctor.server.routes

import com.nowiwr01p.model.api.route.RouteError
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

abstract class BaseRouting {

    protected suspend inline fun <reified T> RoutingContext.sendNoRequestError(): Nothing {
        sendError(
            code = HttpStatusCode.InternalServerError,
            message = "No ${T::class.java.simpleName} provided or it's serialized wrong."
        )
    }

    protected suspend fun RoutingContext.sendInternalError(message: String?): Nothing {
        sendError(
            code = HttpStatusCode.InternalServerError,
            message = message ?: SERVER_ERROR
        )
    }

    protected suspend fun RoutingContext.sendError(code: HttpStatusCode, message: String): Nothing {
        val error = RouteError(code = code.value, message = message)
        call.respond(code, error)
        throw error
    }

    private companion object {
        const val SERVER_ERROR = "Unexpected error"
    }
}