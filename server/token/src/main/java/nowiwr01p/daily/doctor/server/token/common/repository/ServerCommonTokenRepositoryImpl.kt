package nowiwr01p.daily.doctor.server.token.common.repository

import com.nowiwr01p.model.const.Symbols
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.security.SecureRandom
import kotlin.math.max

class ServerCommonTokenRepositoryImpl(
    private val dispatchers: AppDispatchers
): ServerCommonTokenRepository {

    override suspend fun generateToken() = withContext(dispatchers.io) {
        val secureRandom = SecureRandom()

        val deferredList = buildList {
            var flattenList = Symbols.allSymbols
                .map { it.toCharArray().toList() }
                .flatten()
                .shuffled()
            repeat(secureRandom.nextInt(16, 32)) {
                val deferred = async {
                    flattenList = flattenList.shuffled()
                    flattenList
                }
                add(deferred)
            }
        }

        val flattenList = deferredList
            .awaitAll()
            .flatten()

        val tokenList = buildList {
            val lastIndex = flattenList.lastIndex
            repeat(32) {
                val deferred = async {
                    val randomIndex = secureRandom.nextInt(0, max(1, lastIndex))
                    flattenList[randomIndex]
                }
                add(deferred)
            }
        }

        tokenList.awaitAll().joinToString(separator = "")
    }
}