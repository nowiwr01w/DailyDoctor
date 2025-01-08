package nowiwr01p.daily.doctor.server.data.repository.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig
import nowiwr01p.daily.doctor.database.domain.repository.brand_config.DatabaseBrandConfigRepository
import nowiwr01p.daily.doctor.server.domain.repository.brand_config.ServerBrandConfigRepository

class ServerBrandConfigRepositoryImpl(
    private val databaseBrandConfigRepository: DatabaseBrandConfigRepository
): ServerBrandConfigRepository {

    override suspend fun getBrandConfig(): BrandConfig {
        return databaseBrandConfigRepository.getBrandConfig()
    }
}