package repository.brand

import model.brand.AppBrand

interface AppBrandRepository {
    suspend fun getAppBrand(): AppBrand
}