package com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items

import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.item.OnboardingItemsData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.Savings
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian

/**
 * DATA
 */
abstract class SavingsData(override val language: Language): OnboardingItemsData(
    type = Savings,
    language = language
)

/**
 * GEORGIAN
 */
internal data class SavingsDataGeorgian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "ექონომია ექიმთან პირად ვიზიტებზე",
        description = "აგრეთვე ექსკლუზიური ფასდაკლებები ანალიზებზე 25%",
        firstButtonText = "შესანიშნავია!",
        secondButtonText = ""
    )
) : SavingsData(language = Georgian)

/**
 * ENGLISH
 */
internal data class SavingsDataEnglish(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Savings on in-person doctor visits",
        description = "And exclusive discounts on tests up to 25%",
        firstButtonText = "Excellent!",
        secondButtonText = ""
    )
) : SavingsData(language = English)

/**
 * RUSSIAN
 */
internal data class SavingsDataRussian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Экономия на очных визитах к врачу",
        description = "А ещё эксклюзивные скидки на анализы до 25%",
        firstButtonText = "Отлично!",
        secondButtonText = ""
    )
) : SavingsData(language = Russian)

/**
 * UKRAINIAN
 */
internal data class SavingsDataUkrainian(
    override val itemData: OnboardingItemData = OnboardingItemData(
        image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
        title = "Економія на очних візитах до лікаря",
        description = "А ще ексклюзивні знижки на аналізи до 25%",
        firstButtonText = "Чудово!",
        secondButtonText = ""
    )
) : SavingsData(language = Ukrainian)