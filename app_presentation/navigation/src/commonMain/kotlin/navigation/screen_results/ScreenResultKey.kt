package navigation.screen_results

import com.nowiwr01p.model.resources.language.Language

sealed interface ScreenResultKey<T: Any> {
    data object SelectLanguageResultKey: ScreenResultKey<Language>
}
