package com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items

import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.item.OnboardingItemsData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.OnlineAppointment
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian

/**
 * DATA
 */
abstract class OnlineAppointmentData(override val language: Language): OnboardingItemsData(
    type = OnlineAppointment,
    language = language
)

/**
 * GEORGIAN
 */
internal data class OnlineAppointmentDataGeorgian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "ონლაინ კონსულტაციები ექიმებთან ჩათში",
        description = "სახლში, აგარაკზე, შვებულებაში ან სამსახურში",
        firstButtonText = "ძალიან საინტერესოა!",
        secondButtonText = ""
    )
): OnlineAppointmentData(language = Georgian)

/**
 * ENGLISH
 */
internal data class OnlineAppointmentDataEnglish(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Online consultations with doctors in chat",
        description = "At home, at the cottage, on vacation, or even at work",
        firstButtonText = "Very interesting!",
        secondButtonText = ""
    )
): OnlineAppointmentData(language = English)

/**
 * RUSSIAN
 */
internal data class OnlineAppointmentDataRussian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Онлайн-консультации с врачами в чате",
        description = "Дома, на даче, в отпуске или даже на работе",
        firstButtonText = "Очень интересно!",
        secondButtonText = ""
    )
): OnlineAppointmentData(language = Russian)

/**
 * UKRAINIAN
 */
internal data class OnlineAppointmentDataUkrainian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Онлайн-консультації з лікарями в чаті",
        description = "Вдома, на дачі, у відпустці або навіть на роботі",
        firstButtonText = "Дуже цікаво!",
        secondButtonText = ""
    )
) : OnlineAppointmentData(language = Ukrainian)