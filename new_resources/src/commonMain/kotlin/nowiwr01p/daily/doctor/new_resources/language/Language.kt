package nowiwr01p.daily.doctor.new_resources.language

import nowiwr01p.daily.doctor.new_resources.language.Language.English
import nowiwr01p.daily.doctor.new_resources.language.Language.Georgian
import nowiwr01p.daily.doctor.new_resources.language.Language.Russian
import nowiwr01p.daily.doctor.new_resources.language.Language.Ukrainian

sealed class Language(
    val code: String,
    val name: String
) {
    data object Georgian: Language(
        code = "ge",
        name = "ქართული"
    )
    data object English: Language(
        code = "en",
        name = "English"
    )
    data object Russian: Language(
        code = "ru",
        name = "Русский"
    )
    data object Ukrainian: Language(
        code = "ua",
        name = "Українська"
    )
}

val appLanguages = listOf(
    Georgian,
    English,
    Russian,
    Ukrainian
)
