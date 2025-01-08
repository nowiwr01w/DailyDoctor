package nowiwr01p.daily.doctor.server.domain.repository.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig

interface ServerBrandConfigRepository {
    suspend fun getBrandConfig(): BrandConfig
}