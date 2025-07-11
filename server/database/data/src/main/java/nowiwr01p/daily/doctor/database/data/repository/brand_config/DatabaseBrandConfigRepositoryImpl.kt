package nowiwr01p.daily.doctor.database.data.repository.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig
import nowiwr01p.daily.doctor.database.domain.repository.brand_config.DatabaseBrandConfigRepository
import nowiwr01p.daily.doctor.database.domain.storage.brand_config.DatabaseBrandConfigStorage

class DatabaseBrandConfigRepositoryImpl(
    private val storage: DatabaseBrandConfigStorage
): DatabaseBrandConfigRepository {

    override suspend fun getBrandConfig(): BrandConfig {
        return storage.getBrandConfig()
    }
}