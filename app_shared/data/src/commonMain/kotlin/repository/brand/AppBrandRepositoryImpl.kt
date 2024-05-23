package repository.brand

import core.model.brand.AppBrand

class AppBrandRepositoryImpl: AppBrandRepository {
    override suspend fun getAppBrand() = AppBrand.AppBrandClassic()
}