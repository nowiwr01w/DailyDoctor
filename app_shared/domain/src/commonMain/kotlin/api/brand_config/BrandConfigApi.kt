package api.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig

interface BrandConfigApi {
    suspend fun loadBrandConfig(): BrandConfig
}