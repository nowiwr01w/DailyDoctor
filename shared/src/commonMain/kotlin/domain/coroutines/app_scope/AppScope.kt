package domain.coroutines.app_scope

import kotlinx.coroutines.CoroutineScope

interface AppScope {
    val scope: CoroutineScope
}