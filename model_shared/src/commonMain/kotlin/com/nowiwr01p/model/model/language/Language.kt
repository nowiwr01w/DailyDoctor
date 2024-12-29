package com.nowiwr01p.model.model.language

import com.nowiwr01p.model.model.language.Language.English
import com.nowiwr01p.model.model.language.Language.Georgian
import com.nowiwr01p.model.model.language.Language.Russian
import com.nowiwr01p.model.model.language.Language.Ukrainian
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_flag_georgia
import nowiwr01p.daily.doctor.resources.ic_flag_russia
import nowiwr01p.daily.doctor.resources.ic_flag_ukraine
import nowiwr01p.daily.doctor.resources.ic_flag_us
import nowiwr01p.daily.doctor.resources.language_english
import nowiwr01p.daily.doctor.resources.language_georgian
import nowiwr01p.daily.doctor.resources.language_russian
import nowiwr01p.daily.doctor.resources.language_ukrainian
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class Language(
    val code: String,
    val name: StringResource,
    val icon: DrawableResource
) {
    data object English : Language(
        code = "en",
        name = Res.string.language_english,
        icon = Res.drawable.ic_flag_us
    )
    data object Russian : Language(
        code = "ru",
        name = Res.string.language_russian,
        icon = Res.drawable.ic_flag_russia
    )
    data object Georgian : Language(
        code = "ge",
        name = Res.string.language_georgian,
        icon = Res.drawable.ic_flag_georgia
    )
    data object Ukrainian : Language(
        code = "ua",
        name = Res.string.language_ukrainian,
        icon = Res.drawable.ic_flag_ukraine
    )
}

object LanguageSerializer : KSerializer<Language> {
    override val descriptor = PrimitiveSerialDescriptor(
        serialName = "Language",
        kind = PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: Language) {
        encoder.encodeString(value.code)
    }

    override fun deserialize(decoder: Decoder) = when (val code = decoder.decodeString()) {
        English.code -> English
        Russian.code -> Russian
        Georgian.code -> Georgian
        Ukrainian.code -> Ukrainian
        else -> throw IllegalArgumentException("Unknown language code: $code")
    }
}

val appLanguages = listOf(
    Georgian,
    English,
    Russian,
    Ukrainian
)
