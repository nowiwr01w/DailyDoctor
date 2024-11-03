package calldoctor.app_shared.config.domain.usecase

import calldoctor.app_shared.config.config.AppConfig
import com.nowiwr01p.model.usecase.UseCase

interface GetAppConfigUseCase: UseCase<Unit, AppConfig>