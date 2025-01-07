package com.nowiwr01p.model.resources.component_with_resources.dialogs.select_language

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.AppComponentWithResources
import com.nowiwr01p.model.resources.component_with_resources.dialogs.AppDialogWithTranslatedResources.SelectLanguageDialog
import com.nowiwr01p.model.resources.language.Language

@Composable
fun SelectLanguageDialogResources(
    language: Language,
    content: @Composable (resources: SelectLanguageResources) -> Unit
) {
    AppComponentWithResources<SelectLanguageResources>(
        component = SelectLanguageDialog,
        language = language,
        content = content
    )
}