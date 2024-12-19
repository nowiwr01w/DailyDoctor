package view_model

import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.Serializable

@Serializable
class BaseViewModelComponent(
    private val childComponentContext: ComponentContext
): ComponentContext by childComponentContext
