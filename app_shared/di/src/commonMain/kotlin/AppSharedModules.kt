import api.moduleAppSharedApi
import di.works.moduleAppSharedWorks
import network.moduleAppSharedNetwork
import nowiwr01p.daily.doctor.base_api_client.di.moduleBaseApiClient
import repository.moduleAppSharedRepository
import usecase.moduleAppSharedUseCase
import validators.moduleAppSharedValidators

private val allAppSharedModules = listOf(
    moduleBaseApiClient,
    moduleAppSharedApi,
    moduleAppSharedNetwork,
    moduleAppSharedRepository,
    moduleAppSharedUseCase,
    moduleAppSharedValidators,
    moduleAppSharedWorks
)

fun getAppSharedModules() = allAppSharedModules