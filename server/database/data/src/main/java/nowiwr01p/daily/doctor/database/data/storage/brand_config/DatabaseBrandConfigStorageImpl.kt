package nowiwr01p.daily.doctor.database.data.storage.brand_config

import com.nowiwr01p.model.model.app_config.config.AppConfig
import nowiwr01p.daily.doctor.database.domain.storage.brand_config.DatabaseBrandConfigStorage

class DatabaseBrandConfigStorageImpl: DatabaseBrandConfigStorage {
    override suspend fun getBrandConfig() = AppConfig() // TODO: Move to PostgreSQL
}