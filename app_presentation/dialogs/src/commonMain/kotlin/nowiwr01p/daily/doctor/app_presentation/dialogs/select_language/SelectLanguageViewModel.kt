package nowiwr01p.daily.doctor.app_presentation.dialogs.select_language

import com.nowiwr01p.model.model.language.Language
import components.button.ButtonState
import kotlinx.coroutines.delay
import manager.language.AppLanguageManager
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Effect.CloseDialog
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Event.OnCloseClicked
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Event.OnConfirmSelectedLanguage
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.Event.OnSelectLanguageClicked
import pro.respawn.flowmvi.api.PipelineContext
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class SelectLanguageViewModel(
    private val appLanguageManager: AppLanguageManager
): BaseViewModel<State, Event, Effect>(initialValue = State()) {
    /**
     * INIT
     */
    override suspend fun Ctx.init() {
        getAppLanguagesData()
    }

    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is OnCloseClicked -> setEffect(CloseDialog)
            is OnSelectLanguageClicked -> selectLanguage(event.language)
            is OnConfirmSelectedLanguage -> confirmLanguageSelection()
        }
    }

    /**
     * APP LANGUAGES
     */
    private fun Ctx.getAppLanguagesData() = io {
        appLanguageManager.getAppLanguagesData().collect { data ->
            setState { copy(allLanguages = data.allLanguages, selectedLanguage = data.selectedLanguage) }
        }
    }

    /**
     * SELECT LANGUAGE
     */
    private suspend fun Ctx.selectLanguage(language: Language) {
        setState { copy(selectedLanguage = language) }
    }

    private suspend fun Ctx.confirmLanguageSelection() = withState {
        setState { copy(selectButtonState = ButtonState.ORANGE_ACTIVE_PROGRESS) }
        delay(1500)
        appLanguageManager.selectLanguage(selectedLanguage)
        setState { copy(selectButtonState = ButtonState.SUCCESS) }
        delay(1500)
        setEffect(CloseDialog)
    }
}
