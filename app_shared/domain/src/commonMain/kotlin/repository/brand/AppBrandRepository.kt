package repository.brand

import core.model.brand.AppBrand

interface AppBrandRepository {
    suspend fun getAppBrand(): AppBrand
}