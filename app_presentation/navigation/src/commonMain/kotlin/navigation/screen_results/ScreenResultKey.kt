package navigation.screen_results

import nowiwr01p.daily.doctor.new_resources.language.Language

sealed interface ScreenResultKey<T: Any> {
    data object SelectLanguageResultKey: ScreenResultKey<Language>
}
