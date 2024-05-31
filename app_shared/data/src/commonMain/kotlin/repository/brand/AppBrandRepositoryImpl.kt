package repository.brand

import model.brand.AppBrand

class AppBrandRepositoryImpl: AppBrandRepository {
    override suspend fun getAppBrand() = AppBrand.AppBrandClassic()
}