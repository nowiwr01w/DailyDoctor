package usecase.language

import com.nowiwr01p.model.usecase.UseCase
import com.nowiwr01p.model.resources.language.Language

interface GetAppLanguagesUseCase: UseCase<Unit, List<Language>>
