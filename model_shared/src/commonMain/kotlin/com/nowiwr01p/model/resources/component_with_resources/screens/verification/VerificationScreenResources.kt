package com.nowiwr01p.model.resources.component_with_resources.screens.verification

import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentTranslatedResources

/**
 * DATA
 */
sealed interface VerificationScreenResources: ComponentResources {
    val verificationTitle: String
    val verificationCodeSentDescription: String
    val verificationNewCodeRequired: String
    val verificationSendCodeAgain: String
    val confirm: String
}

/**
 * TRANSLATIONS
 */
internal data class VerificationScreenResourcesRussian(
    override val verificationTitle: String = "Верификация",
    override val verificationCodeSentDescription: String = "Мы отправили код на указанный вами номер. Введите его в поле ниже.",
    override val verificationNewCodeRequired: String = "Мне нужен новый код",
    override val verificationSendCodeAgain: String = "Не пришёл код?\nОтправим ещё один через {1} сек",
    override val confirm: String = "Подтвердить"
): VerificationScreenResources

internal data class VerificationScreenResourcesEnglish(
    override val verificationTitle: String = "Verification",
    override val verificationCodeSentDescription: String = "We have sent a code to the number you provided. Enter it in the field below.",
    override val verificationNewCodeRequired: String = "I need a new code",
    override val verificationSendCodeAgain: String = "Didn't get the code?\nWe'll send another in {1} sec",
    override val confirm: String = "Confirm"
): VerificationScreenResources

internal data class VerificationScreenResourcesUkrainian(
    override val verificationTitle: String = "Верифікація",
    override val verificationCodeSentDescription: String = "Ми відправили код на вказаний вами номер. Введіть його у поле нижче.",
    override val verificationNewCodeRequired: String = "Мені потрібен новий код",
    override val verificationSendCodeAgain: String = "Не отримали код?\nМи надішлемо ще один через {1} сек",
    override val confirm: String = "Підтвердити"
): VerificationScreenResources

internal data class VerificationScreenResourcesGeorgian(
    override val verificationTitle: String = "ვერიფიკაცია",
    override val verificationCodeSentDescription: String = "ჩვენ გავაგზავნეთ კოდი მითითებულ ნომერზე. შეიყვანეთ იგი ქვემოთ მოცემულ ველში.",
    override val verificationNewCodeRequired: String = "მე მჭირდება ახალი კოდი",
    override val verificationSendCodeAgain: String = "არ მოვიდა კოდი?\nგამოგიგზავნით კიდევ ერთს {1} წამში",
    override val confirm: String = "დადასტურება"
): VerificationScreenResources

/**
 * SCREEN RESOURCES
 */
internal class VerificationScreenTranslatedResources: ComponentTranslatedResources<VerificationScreenResources>() {
    override fun getGeorgianResources() = VerificationScreenResourcesGeorgian()
    override fun getEnglishResources() = VerificationScreenResourcesEnglish()
    override fun getRussianResources() = VerificationScreenResourcesRussian()
    override fun getUkrainianResources() = VerificationScreenResourcesUkrainian()
}