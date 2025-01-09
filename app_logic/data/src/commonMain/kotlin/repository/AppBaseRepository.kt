package repository

import com.nowiwr01p.model.repository.BaseRepository
import com.nowiwr01p.model.usecase.execute
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsGetLanguageUseCase
import org.koin.core.component.inject

abstract class AppBaseRepository: BaseRepository() {
    /**
     * DATA
     */
    private val appPrefsGetLanguageUseCase by inject<AppPrefsGetLanguageUseCase>()

    /**
     * APP LANGUAGE
     */
    protected suspend fun getAppLanguage() = appPrefsGetLanguageUseCase.execute()
}