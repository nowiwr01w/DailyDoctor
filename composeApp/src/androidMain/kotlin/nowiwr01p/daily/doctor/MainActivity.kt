package nowiwr01p.daily.doctor

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import platform.navigation.getMainComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainComponent = getMainComponent(defaultComponentContext())
        setContent {
            App(mainComponent)
        }
    }
}