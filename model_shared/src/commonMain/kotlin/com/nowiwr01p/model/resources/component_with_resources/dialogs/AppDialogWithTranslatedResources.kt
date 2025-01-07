package com.nowiwr01p.model.resources.component_with_resources.dialogs

import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentTranslatedResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentWithTranslatedResources
import com.nowiwr01p.model.resources.component_with_resources.dialogs.select_language.SelectLanguageResources
import com.nowiwr01p.model.resources.component_with_resources.dialogs.select_language.SelectLanguageTranslatedResources

internal sealed class AppDialogWithTranslatedResources<T: ComponentResources>(
    override val resources: ComponentTranslatedResources<T>
): ComponentWithTranslatedResources<T>(resources) {
    /**
     * SELECT LANGUAGE
     */
    data object SelectLanguageDialog: AppDialogWithTranslatedResources<SelectLanguageResources>(
        resources = SelectLanguageTranslatedResources()
    )
}