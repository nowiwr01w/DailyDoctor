import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner

class GlobalInstanceKeeperOwner: InstanceKeeperOwner {
    override val instanceKeeper = InstanceKeeperDispatcher()
}
