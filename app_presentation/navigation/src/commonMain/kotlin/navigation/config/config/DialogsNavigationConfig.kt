package navigation.config.config

import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationConfig
import navigation.config.child.DialogsChild

@Serializable
sealed class DialogsNavigationConfig(val child: DialogsChild): BaseNavigationConfig
