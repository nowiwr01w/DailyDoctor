package com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items

import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.item.OnboardingItemsData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.UnlimitedCommunication
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian

/**
 * DATA
 */
abstract class UnlimitedCommunicationData(override val language: Language): OnboardingItemsData(
    type = UnlimitedCommunication,
    language = language
)

/**
 * GEORGIAN
 */
internal data class UnlimitedCommunicationDataGeorgian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "შეუზღუდავი მოთხოვნები - ჩვენ 24/7 ხელმისაწვდომები ვართ",
        description = "დაწერეთ, როცა გტკივათ ან უბრალოდ გჭირდებათ კითხვა",
        firstButtonText = "ძალიან კარგი, კიდევ რა?",
        secondButtonText = ""
    )
): UnlimitedCommunicationData(language = Georgian)

/**
 * ENGLISH
 */
internal data class UnlimitedCommunicationDataEnglish(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Unlimited requests - we are available 24/7",
        description = "Write when it hurts or when you just need to ask",
        firstButtonText = "Great, what else?",
        secondButtonText = ""
    )
): UnlimitedCommunicationData(language = English)

/**
 * RUSSIAN
 */
internal data class UnlimitedCommunicationDataRussian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Безлимитные обращения - мы на связи 24/7",
        description = "Пишите, когда болит или когда нужно просто спросить",
        firstButtonText = "Класс, а что еще?",
        secondButtonText = ""
    )
): UnlimitedCommunicationData(language = Russian)

/**
 * UKRAINIAN
 */
internal data class UnlimitedCommunicationDataUkrainian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Безлімітні звернення – ми на звʼязку 24/7",
        description = "Пишіть, коли болить або коли потрібно просто запитати",
        firstButtonText = "Клас, а що ще?",
        secondButtonText = ""
    )
) : UnlimitedCommunicationData(language = Ukrainian)