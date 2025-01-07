package com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items

import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.item.OnboardingItemsData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.ForWholeFamily
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian

/**
 * DATA
 */
abstract class ForWholeFamilyData(override val language: Language): OnboardingItemsData(
    type = ForWholeFamily,
    language = language
)

/**
 * GEORGIAN
 */
internal data class ForWholeFamilyDataGeorgian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "მთელი ოჯახისთვის - თქვენთვის, ბავშვებისთვის და მშობლებისთვის",
        description = "და ქმრისთვის, რომელიც თავად ექიმთან არ წავიდოდა",
        firstButtonText = "ეს მჭირდება",
        secondButtonText = ""
    )
): ForWholeFamilyData(language = Georgian)

/**
 * ENGLISH
 */
internal data class ForWholeFamilyDataEnglish(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "For the whole family - yourself, children, and parents",
        description = "And for the husband who wouldn't go to the doctor himself",
        firstButtonText = "I need this",
        secondButtonText = ""
    )
): ForWholeFamilyData(language = English)

/**
 * RUSSIAN
 */
internal data class ForWholeFamilyDataRussian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Для всей семьи - себе, детям и родителям",
        description = "И мужу, который сам бы не пошел к врачу",
        firstButtonText = "Это мне надо",
        secondButtonText = ""
    )
): ForWholeFamilyData(language = Russian)

/**
 * UKRAINIAN
 */
internal data class ForWholeFamilyDataUkrainian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Для всієї родини – для себе, дітей і батьків",
        description = "І для чоловіка, який сам би не пішов до лікаря",
        firstButtonText = "Це мені потрібно",
        secondButtonText = ""
    )
) : ForWholeFamilyData(language = Ukrainian)