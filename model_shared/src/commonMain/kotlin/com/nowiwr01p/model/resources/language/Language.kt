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
    @Serializable
    data object Georgian: Language(
        code = "ge",
        name = "ქართული"
    )

    @Serializable
    data object English: Language(
        code = "en",
        name = "English"
    )

    @Serializable
    data object Russian: Language(
        code = "ru",
        name = "Русский"
    )

    @Serializable
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
val defaultSelectedLanguage = Georgian
