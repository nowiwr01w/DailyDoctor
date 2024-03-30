package platform.navigation

import com.arkivanov.decompose.ComponentContext
import navigation.MainNavigatorImpl

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect fun getMainComponent(context: ComponentContext): MainNavigatorImpl