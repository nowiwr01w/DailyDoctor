package usecase.brand

import repository.brand.AppBrandRepository

class AppGetBrandUseCaseImpl(
    private val repository: AppBrandRepository
): AppGetBrandUseCase {

    override suspend fun execute(input: Unit) = repository.getAppBrand()
}