package com.nowiwr01p.model.resources.component_with_resources.dialogs.select_language

import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentTranslatedResources

/**
 * DATA
 */
sealed interface SelectLanguageResources: ComponentResources {
    val appLanguageTitle: String
    val selectButtonText: String
    val changeInSettingsText: String
}

/**
 * TRANSLATIONS
 */
internal data class SelectLanguageResourcesGeorgian(
    override val appLanguageTitle: String = "აპის ენა",
    override val selectButtonText: String = "არჩევა",
    override val changeInSettingsText: String = "თქვენ ყოველთვის შეგიძლიათ შეცვალოთ ენა პარამეტრებში"
) : SelectLanguageResources

internal data class SelectLanguageResourcesEnglish(
    override val appLanguageTitle: String = "App language",
    override val selectButtonText: String = "Select",
    override val changeInSettingsText: String = "You can always change the language in settings"
) : SelectLanguageResources

internal data class SelectLanguageResourcesRussian(
    override val appLanguageTitle: String = "Язык приложения",
    override val selectButtonText: String = "Выбрать",
    override val changeInSettingsText: String = "Вы всегда сможете изменить язык в настройках"
): SelectLanguageResources

internal data class SelectLanguageResourcesUkrainian(
    override val appLanguageTitle: String = "Мова застосунку",
    override val selectButtonText: String = "Вибрати",
    override val changeInSettingsText: String = "Ви завжди можете змінити мову в налаштуваннях"
) : SelectLanguageResources

/**
 * SCREEN RESOURCES
 */
internal class SelectLanguageTranslatedResources: ComponentTranslatedResources<SelectLanguageResources>() {
    override fun getGeorgianResources() = SelectLanguageResourcesGeorgian()
    override fun getEnglishResources() = SelectLanguageResourcesEnglish()
    override fun getRussianResources() = SelectLanguageResourcesRussian()
    override fun getUkrainianResources() = SelectLanguageResourcesUkrainian()
}