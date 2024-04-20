package data.usecase.brand

import domain.repository.brand.AppBrandRepository
import domain.usecase.brand.GetAppBrandUseCase

class GetAppBrandUseCaseImpl(
    private val repository: AppBrandRepository
): GetAppBrandUseCase {

    override suspend fun execute(input: Unit) = repository.getAppBrand()
}