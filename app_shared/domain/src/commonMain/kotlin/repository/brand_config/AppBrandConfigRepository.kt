package repository.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig

interface AppBrandConfigRepository {
    suspend fun loadBrandConfig(): BrandConfig
}