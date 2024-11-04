package usecase.brand_config

import repository.brand_config.AppBrandConfigRepository

class AppGetBrandConfigUseCaseImpl(
    private val repository: AppBrandConfigRepository
): AppGetBrandConfigUseCase {

    override suspend fun execute(input: Unit) = repository.loadBrandConfig()
}