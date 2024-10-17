package nowiwr01p.daily.doctor.server.data.repository.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.repository.BaseRepository
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.token.common.usecase.ServerGenerateCommonTokenUseCase

class ServerPinCodeRepositoryImpl(
    private val repository: DatabasePinCodeRepository,
    private val generateCommonTokenUseCase: ServerGenerateCommonTokenUseCase
): BaseRepository(), ServerPinCodeRepository {

    override suspend fun createPinCode(request: CreatePinCodeRequest) = io {
        val authToken = generateCommonTokenUseCase.execute()
        repository.createPinCode(authToken, request)
    }

    override suspend fun checkPinCode(request: CheckPinCodeRequest) = io {
        val authToken = generateCommonTokenUseCase.execute()
        repository.checkPinCode(authToken, request)
    }

    override suspend fun changePinCode(request: ChangePinCodeRequest) = io {
        repository.changePinCode(request)
    }

    override suspend fun deletePinCode() = io {
        repository.deletePinCode()
    }
}