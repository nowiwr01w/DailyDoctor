package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.screen_info

import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentTranslatedResources

/**
 * DATA
 */
sealed interface SubscriptionScreenResources: ComponentResources {
    val choosePlanTitle: String
    val year: String
    val continueWithYearlyPriceText: String
    val continueWithMonthlyPriceText: String
    val continueWithoutBenefits: String
    val agreementText: String
}

/**
 * TRANSLATIONS
 */
internal data class SubscriptionScreenResourcesGeorgian(
    override val choosePlanTitle: String = "აირჩიეთ საშვი",
    override val year: String = "წელი",
    override val continueWithYearlyPriceText: String = "ყიდვა წლიური საშვის {1}$",
    override val continueWithMonthlyPriceText: String = "ყიდვა ყოველთვიური საშვის {1}$",
    override val continueWithoutBenefits: String = "გაგრძელება პრივილეგიების გარეშე",
    override val agreementText: String = "გაგრძელებით, თქვენ ეთანხმებით კონფიდენციალობის პოლიტიკასა და გამოყენების პირობებს"
) : SubscriptionScreenResources

internal data class SubscriptionScreenResourcesEnglish(
    override val choosePlanTitle: String = "Choose your pass",
    override val year: String = "Year",
    override val continueWithYearlyPriceText: String = "Purchase yearly pass for {1}$",
    override val continueWithMonthlyPriceText: String = "Purchase monthly pass for {1}$",
    override val continueWithoutBenefits: String = "Continue without benefits",
    override val agreementText: String = "By continuing, you agree to the Privacy Policy and Terms of Use"
) : SubscriptionScreenResources

internal data class SubscriptionScreenResourcesRussian(
    override val choosePlanTitle: String = "Выберите пропуск",
    override val year: String = "Год",
    override val continueWithYearlyPriceText: String = "Купить годовой пропуск за {1}$",
    override val continueWithMonthlyPriceText: String = "Купить месячный пропуск за {1}$",
    override val continueWithoutBenefits: String = "Продолжить без привилегий",
    override val agreementText: String = "Продолжая, вы соглашаетесь с политикой конфиденциальности и условиями пользования"
): SubscriptionScreenResources

internal data class SubscriptionScreenResourcesUkrainian(
    override val choosePlanTitle: String = "Виберіть пропуск",
    override val year: String = "Рік",
    override val continueWithYearlyPriceText: String = "Придбати річний пропуск за {1}$",
    override val continueWithMonthlyPriceText: String = "Придбати місячний пропуск за {1}$",
    override val continueWithoutBenefits: String = "Продовжити без привілеїв",
    override val agreementText: String = "Продовжуючи, ви погоджуєтесь з політикою конфіденційності та умовами використання"
) : SubscriptionScreenResources

/**
 * SCREEN RESOURCES
 */
internal class SubscriptionScreenTranslatedResources: ComponentTranslatedResources<SubscriptionScreenResources>() {
    override fun getGeorgianResources() = SubscriptionScreenResourcesGeorgian()
    override fun getEnglishResources() = SubscriptionScreenResourcesEnglish()
    override fun getRussianResources() = SubscriptionScreenResourcesRussian()
    override fun getUkrainianResources() = SubscriptionScreenResourcesUkrainian()
}