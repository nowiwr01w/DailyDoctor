import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

actual fun startLogger() {
    Napier.base(DebugAntilog())
}

actual fun logMessage(message: String) = Napier.log(
    priority = LogLevel.DEBUG,
    tag = "Zhopa",
    message = "[iOS] $message"
)