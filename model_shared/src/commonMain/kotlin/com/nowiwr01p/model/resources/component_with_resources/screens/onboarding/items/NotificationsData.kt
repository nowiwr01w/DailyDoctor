package com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items

import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.item.OnboardingItemsData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.Notifications
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian

/**
 * DATA
 */
abstract class NotificationsData(override val language: Language): OnboardingItemsData(
    type = Notifications,
    language = language
)

/**
 * GEORGIAN
 */
internal data class NotificationsDataGeorgian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "არ გამოტოვოთ ექიმის პასუხი თქვენს შეტყობინებაზე",
        description = "და მშვიდად დაკავდით სხვა საქმეებით",
        firstButtonText = "ნება დართეთ შეტყობინებებს",
        secondButtonText = "ახლა არა"
    )
): NotificationsData(language = Georgian)

/**
 * ENGLISH
 */
internal data class NotificationsDataEnglish(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Don't miss the doctor's response to your message",
        description = "And calmly do other things",
        firstButtonText = "Allow notifications",
        secondButtonText = "Not now"
    )
): NotificationsData(language = English)

/**
 * RUSSIAN
 */
internal data class NotificationsDataRussian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Не пропустите ответ врача на ваше сообщение",
        description = "И спокойно занимайтесь другими делами",
        firstButtonText = "Разрешить уведомления",
        secondButtonText = "Не сейчас"
    )
): NotificationsData(language = Russian)

/**
 * UKRAINIAN
 */
internal data class NotificationsDataUkrainian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Не пропустіть відповідь лікаря на ваше повідомлення",
        description = "І спокійно займайтеся іншими справами",
        firstButtonText = "Дозволити сповіщення",
        secondButtonText = "Зараз не потрібно"
    )
) : NotificationsData(language = Ukrainian)