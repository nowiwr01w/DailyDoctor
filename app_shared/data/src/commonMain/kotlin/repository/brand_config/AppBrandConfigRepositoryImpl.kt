package repository.brand_config

import api.brand_config.BrandConfigApi
import repository.AppBaseRepository

class AppBrandConfigRepositoryImpl(
    private val api: BrandConfigApi
): AppBaseRepository(), AppBrandConfigRepository {

    override suspend fun loadBrandConfig() = io {
        api.loadBrandConfig()
    }
}