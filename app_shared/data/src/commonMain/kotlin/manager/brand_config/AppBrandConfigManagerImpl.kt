package manager.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import usecase.brand_config.AppGetBrandConfigUseCase

class AppBrandConfigManagerImpl(
    private val appGetBrandConfigUseCase: AppGetBrandConfigUseCase
): AppBrandConfigManager {

    private val brandConfig = MutableStateFlow<BrandConfig?>(null)

    override suspend fun getBrandConfig(fromRemote: Boolean): Flow<BrandConfig> {
        if (fromRemote) {
            val config = appGetBrandConfigUseCase.execute()
            brandConfig.emit(config)
        }
        return brandConfig.filterNotNull()
    }
}