package model.language

import com.nowiwr01p.model.resources.language.Language

data class AppLanguageData(
    val allLanguages: List<Language> = listOf(),
    val selectedLanguage: Language = Language.Georgian
)
