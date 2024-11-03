package calldoctor.app_shared.config.data.usecase

import calldoctor.app_shared.config.domain.repository.AppConfigRepository
import calldoctor.app_shared.config.domain.usecase.GetAppConfigUseCase

class GetAppConfigUseCaseImpl(
    private val repository: AppConfigRepository
): GetAppConfigUseCase {

    override suspend fun execute(input: Unit) = repository.loadAppConfig()
}