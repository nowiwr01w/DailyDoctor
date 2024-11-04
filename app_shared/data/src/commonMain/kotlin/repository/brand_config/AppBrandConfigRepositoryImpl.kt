package repository.brand_config

import api.brand_config.BrandConfigApi

class AppBrandConfigRepositoryImpl(
    private val api: BrandConfigApi
): AppBrandConfigRepository {

    override suspend fun loadBrandConfig() = api.loadBrandConfig()
}