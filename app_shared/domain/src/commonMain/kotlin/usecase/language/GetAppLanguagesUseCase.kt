package usecase.language

import com.nowiwr01p.model.model.language.Language
import com.nowiwr01p.model.usecase.UseCase

interface GetAppLanguagesUseCase: UseCase<Unit, List<Language>>