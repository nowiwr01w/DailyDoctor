package nowiwr01p.daily.doctor.server.routes

import com.nowiwr01p.model.api.errors.RouteError
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.header
import io.ktor.server.response.respondText
import io.ktor.server.routing.RoutingContext
import kotlinx.serialization.json.Json
import nowiwr01p.daily.doctor.encryption.server.EncryptionServer
import nowiwr01p.daily.doctor.encryption.shared.data.EncryptedData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRouting: KoinComponent {

    protected val json by inject<Json>()
    protected val encryptionHelper by inject<EncryptionServer>()

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
        encryptionHelper.encodeFromServerToClient(model).let { encryptedModel ->
            call.response.header(
                name = HttpHeaders.ContentLength,
                value = encryptedModel.length.toString()
            )
            call.response.header(
                name = "ascii",
                value = encryptionHelper.getPublicKey()
            )
            call.respondText(
                text = encryptedModel,
                contentType = ContentType.Application.Json,
                status = code
            )
        }
    }

    protected suspend inline fun <reified T> RoutingContext.getParameter(
        name: String,
        paramAsType: (stringParam: String) -> T?,
        missingParameterIsError: Boolean = true,
        noinline validateParameterCallback: ((body: T) -> T)? = null
    ): T? {
        val stringParam = call.queryParameters[name] ?: run {
            if (missingParameterIsError) {
                sendRoutingError(
                    code = HttpStatusCode.BadRequest,
                    message = "No parameter with name=[$name] provided."
                )
            }
            return null
        }
        val param = paramAsType(stringParam) ?: run {
            if (missingParameterIsError) {
                sendRoutingError(
                    code = HttpStatusCode.BadRequest,
                    message = "Cannot convert parameter with name=[$name] to type ${T::class.java.simpleName}."
                )
            }
            return null
        }
        return validateParameterCallback?.invoke(param) ?: param
    }

    protected suspend inline fun <reified T> RoutingContext.getRequestBody(
        noinline validateBodyCallback: ((body: T) -> T)? = null
    ): T? {
        val requestBodyEncryptedData = call.receiveNullable<EncryptedData>() ?: run {
            sendRoutingError(
                code = HttpStatusCode.BadRequest,
                message = "No ${T::class.java.simpleName} provided or it's serialized wrong."
            )
            return null
        }
        return encryptionHelper.decodeOnServerFromClient<T>(requestBodyEncryptedData).let { data ->
            when {
                validateBodyCallback == null -> data
                else -> validateBodyCallback(data)
            }
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