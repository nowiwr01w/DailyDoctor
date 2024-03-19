package base.view_model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.compose.rememberFactory

private typealias BaseVM = BaseViewModel<*, *, *>

@Composable
inline fun <reified VM : BaseVM> rememberViewModel(): VM {
    val scope = rememberCoroutineScope()
    val viewModelFactory by rememberFactory<CoroutineScope, VM>()
    return remember { viewModelFactory(scope) }
}