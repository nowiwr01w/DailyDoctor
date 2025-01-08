package com.nowiwr01p.model.api.errors

import com.nowiwr01p.model.api.route.Route

sealed class HttpClientError(
    override val message: String
): Throwable(message) {
    data class ApiRequestError(
        val route: Route,
        val error: Throwable
    ): HttpClientError(message = buildApiRequestErrorMessage(route, error))

    data class ParsingApiResponseError(
        val route: Route,
        val error: Throwable,
        val responseBodyString: String
    ): HttpClientError(
        message = buildParsingApiRequestErrorMessage(route, error, responseBodyString)
    )
}

/**
 * API REQUEST ERROR
 */
private fun buildApiRequestErrorMessage(route: Route, error: Throwable): String {
    return """
        There is an API error on a route = ${route.route}. 
        Error message = ${error.message}
    """.trimIndent()
}

/**
 * PARSING API REQUEST ERROR
 */
private fun buildParsingApiRequestErrorMessage(
    route: Route,
    error: Throwable,
    responseBodyString: String
): String {
    return """
        We can't parse ResponseJson on a route = ${route.route}.
        Error message = ${error.message}
        ResponseBodyString = $responseBodyString
    """.trimIndent()
}