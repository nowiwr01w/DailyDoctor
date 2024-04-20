package domain.repository.brand

interface AppBrandRepository {
    suspend fun getAppBrand(): AppBrand
}