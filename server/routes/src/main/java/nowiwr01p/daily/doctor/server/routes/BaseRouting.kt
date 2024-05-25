package nowiwr01p.daily.doctor.server.routes

import com.nowiwr01p.model.api.errors.RouteError
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.header
import io.ktor.server.response.respondText
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRouting: KoinComponent {

    protected val json by inject<Json>()

    /**
     * SUCCESS
     */
    protected suspend fun RoutingContext.respondWithSuccessMessage() = respondWithSuccessModel(
        value = RoutingSuccessModel(),
        code = HttpStatusCode.OK
    )

    protected suspend inline fun <reified T> RoutingContext.respondWithSuccessModel(
        value: T,
        code: HttpStatusCode = HttpStatusCode.OK
    ) {
        respondWithModel(model = value, code = code)
    }

    /**
     * ERRORS
     */
    protected suspend fun RoutingContext.sendInternalError(message: String?) = sendRoutingError(
        code = HttpStatusCode.InternalServerError,
        message = message ?: SERVER_ERROR
    )

    protected suspend inline fun <reified T> RoutingContext.sendNoRequestError() = sendRoutingError(
        code = HttpStatusCode.BadRequest,
        message = "No ${T::class.java.simpleName} provided or it's serialized wrong."
    )

    protected suspend fun RoutingContext.sendRoutingError(
        code: HttpStatusCode,
        message: String
    ) {
        respondWithModel(
            code = code,
            model = RouteError(code = code.value, errorMessage = message)
        )
    }

    /**
     * BASE
     */
    protected suspend inline fun <reified T> RoutingContext.respondWithModel(
        model: T,
        code: HttpStatusCode
    ) {
        json.encodeToString(model).let { jsonModel ->
            call.response.header(
                name = HttpHeaders.ContentLength,
                value = jsonModel.length.toString()
            )
            call.respondText(
                text = jsonModel,
                contentType = ContentType.Application.Json,
                status = code
            )
        }
    }

    /**
     * BASE MESSAGES
     */
    private companion object {
        const val OK = "Success operation"
        const val SERVER_ERROR = "Unexpected error"
    }

    private data class RoutingSuccessModel(val message: String = OK)
}

private typealias RoutingContext = PipelineContext<Unit, ApplicationCall>