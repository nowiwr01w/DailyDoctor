import api.moduleAppSharedApi
import di.works.moduleAppSharedWorks
import manager.moduleAppSharedManager
import network.moduleAppSharedNetwork
import nowiwr01p.daily.doctor.base_api_client.di.moduleBaseApiClient
import nowiwr01p.daily.doctor.encryption.di.moduleEncryption
import repository.moduleAppSharedRepository
import usecase.moduleAppSharedUseCase
import validators.moduleAppSharedValidators

private val allAppSharedModules = listOf(
    moduleBaseApiClient,
    moduleAppSharedApi,
    moduleAppSharedNetwork,
    moduleAppSharedRepository,
    moduleAppSharedUseCase,
    moduleAppSharedManager,
    moduleAppSharedValidators,
    moduleAppSharedWorks,
    moduleEncryption
)

fun getAppSharedModules() = allAppSharedModules