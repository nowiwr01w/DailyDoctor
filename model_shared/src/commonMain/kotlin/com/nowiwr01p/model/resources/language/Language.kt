package com.nowiwr01p.model.resources.language

import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian
import kotlinx.serialization.Serializable

@Serializable
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
