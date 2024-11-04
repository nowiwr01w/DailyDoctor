package nowiwr01p.daily.doctor.database.domain.storage.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig
import com.nowiwr01p.model.model.app_config.config.BrandConfigType

interface DatabaseBrandConfigStorage {
    suspend fun getBrandConfig(type: BrandConfigType): BrandConfig
}