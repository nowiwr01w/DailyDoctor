package base.view_model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf

private typealias BaseVM = BaseViewModel<*, *, *>

@Composable
inline fun <reified VM : BaseVM> rememberViewModel(): VM {
    val scope = rememberCoroutineScope()
    val viewModel = getKoin().get<VM> { parametersOf(scope) }
    return remember { viewModel }
}