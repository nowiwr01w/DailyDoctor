package data.repository.brand

import domain.repository.brand.AppBrand.*
import domain.repository.brand.AppBrandRepository

class AppBrandRepositoryImpl: AppBrandRepository {
    override suspend fun getAppBrand() = AppBrandClassic()
}