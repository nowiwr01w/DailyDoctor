package nowiwr01p.daily.doctor.database.domain.repository.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.app_config.config.BrandConfig

interface DatabaseBrandConfigRepository {
    suspend fun getBrandConfig(type: BrandConfigType): BrandConfig
}