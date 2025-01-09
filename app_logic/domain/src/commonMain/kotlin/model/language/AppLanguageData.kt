package model.language

import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.defaultSelectedLanguage

data class AppLanguageData(
    val allLanguages: List<Language> = listOf(),
    val selectedLanguage: Language = defaultSelectedLanguage
)
