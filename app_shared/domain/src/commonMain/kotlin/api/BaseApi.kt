package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

abstract class BaseApi(override val di: DI): DIAware {

    protected val client by instance<HttpClient>()

    protected suspend inline fun <reified T> getHttp(route: String) = client
        .get { url("http://10.0.2.2:8080/$route") }
        .body<T>()
}