package com.nowiwr01p.model.resources.component_with_resources.screens.pin

import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentTranslatedResources

/**
 * DATA
 */
sealed interface PinScreenResources: ComponentResources {
    val pinCodeCreate: String
    val pinCodeEnter: String
    val pinCodeRepeat: String
    val pinCodeCreateNew: String
}

/**
 * TRANSLATIONS
 */
internal data class PinScreenResourcesRussian(
    override val pinCodeCreate: String = "Придумайте пин код",
    override val pinCodeEnter: String = "Введите пин код",
    override val pinCodeRepeat: String = "Повторите пин код",
    override val pinCodeCreateNew: String = "Введите новый пин код"
) : PinScreenResources

internal data class PinScreenResourcesEnglish(
    override val pinCodeCreate: String = "Create a PIN code",
    override val pinCodeEnter: String = "Enter the PIN code",
    override val pinCodeRepeat: String = "Repeat the PIN code",
    override val pinCodeCreateNew: String = "Enter a new PIN code"
) : PinScreenResources

internal data class PinScreenResourcesUkrainian(
    override val pinCodeCreate: String = "Придумайте PIN код",
    override val pinCodeEnter: String = "Введіть PIN код",
    override val pinCodeRepeat: String = "Повторіть PIN код",
    override val pinCodeCreateNew: String = "Введіть новий PIN код"
) : PinScreenResources

internal data class PinScreenResourcesGeorgian(
    override val pinCodeCreate: String = "შექმენით PIN კოდი",
    override val pinCodeEnter: String = "შეიყვანეთ PIN კოდი",
    override val pinCodeRepeat: String = "გაიმეორეთ PIN კოდი",
    override val pinCodeCreateNew: String = "შეიყვანეთ ახალი PIN კოდი"
) : PinScreenResources

/**
 * SCREEN RESOURCES
 */
internal class PinScreenTranslatedResources: ComponentTranslatedResources<PinScreenResources>() {
    override fun getGeorgianResources() = PinScreenResourcesGeorgian()
    override fun getEnglishResources() = PinScreenResourcesEnglish()
    override fun getRussianResources() = PinScreenResourcesRussian()
    override fun getUkrainianResources() = PinScreenResourcesUkrainian()
}