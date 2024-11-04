package nowiwr01p.daily.doctor.server.data.usecase.brand_config

import com.nowiwr01p.model.model.app_config.config.BrandConfig
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import nowiwr01p.daily.doctor.server.domain.repository.brand_config.ServerBrandConfigRepository
import nowiwr01p.daily.doctor.server.domain.usecase.brand_config.ServerGetBrandConfigUseCase

class ServerGetBrandConfigUseCaseImpl(
    private val repository: ServerBrandConfigRepository
): ServerGetBrandConfigUseCase {

    override suspend fun execute(input: BrandConfigType): BrandConfig {
        return repository.getBrandConfig(input)
    }
}