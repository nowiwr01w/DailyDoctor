package nowiwr01p.daily.doctor.local_database

sealed class SettingsKey(
    open val keyName: String
) {
    data object LanguageKey: SettingsKey(keyName = "language")
}
