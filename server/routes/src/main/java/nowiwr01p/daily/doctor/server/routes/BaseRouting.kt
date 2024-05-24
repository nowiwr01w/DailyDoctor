package nowiwr01p.daily.doctor.server.routes

import com.nowiwr01p.model.api.route.RouteError
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private typealias RoutingContext = PipelineContext<Unit, ApplicationCall>

abstract class BaseRouting: KoinComponent {

    protected val json by inject<Json>()

    /**
     * SUCCESS
     */
    protected suspend fun RoutingContext.sendSuccess() = call.respond(
        status = HttpStatusCode.OK,
        message = OK
    )

    protected suspend inline fun <reified T> RoutingContext.sendStringObject(value: T) = call.respond(
        status = HttpStatusCode.OK,
        message = json.encodeToString(value)
    )

    /**
     * ERRORS
     */
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

    /**
     * BASE MESSAGES
     */
    private companion object {
        const val OK = "Ok"
        const val SERVER_ERROR = "Unexpected error"
    }
}