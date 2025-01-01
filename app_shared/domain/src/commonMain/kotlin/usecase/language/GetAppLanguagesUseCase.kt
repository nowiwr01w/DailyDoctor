package usecase.language

import com.nowiwr01p.model.usecase.UseCase
import nowiwr01p.daily.doctor.new_resources.language.Language

interface GetAppLanguagesUseCase: UseCase<Unit, List<Language>>
