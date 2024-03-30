package platform.navigation

import com.arkivanov.decompose.ComponentContext
import navigation.MainNavigatorImpl

actual fun getMainComponent(context: ComponentContext) = MainNavigatorImpl(context = context)