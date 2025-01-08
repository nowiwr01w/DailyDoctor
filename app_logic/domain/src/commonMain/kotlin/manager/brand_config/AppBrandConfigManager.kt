package manager.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig
import kotlinx.coroutines.flow.Flow

interface AppBrandConfigManager {
    suspend fun getBrandConfig(fromRemote: Boolean): Flow<BrandConfig>
}