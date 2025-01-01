package model.language

import nowiwr01p.daily.doctor.new_resources.language.Language

data class AppLanguageData(
    val allLanguages: List<Language> = listOf(),
    val selectedLanguage: Language = Language.Georgian
)
