package view_model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf

private typealias BaseVM = BaseViewModel<*, *, *>

@Composable
inline fun <reified VM: BaseVM> InstanceKeeperOwner.rememberViewModel(vararg params: Any): VM {
    val savedViewModel = instanceKeeper.getOrCreate(
        key = VM::class.simpleName.orEmpty(),
        factory = {
            getKoin().get<VM> { parametersOf(*params) }
        }
    )
    if (!savedViewModel.store.isActive) {
        savedViewModel.startStore()
    }
    return remember { savedViewModel }
}
