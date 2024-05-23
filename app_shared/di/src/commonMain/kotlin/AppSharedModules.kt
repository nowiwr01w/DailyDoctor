import api.moduleAppSharedApi
import di.works.moduleAppSharedWorks
import network.moduleAppSharedNetwork
import repository.moduleAppSharedRepository
import usecase.moduleAppSharedUseCase
import validators.moduleAppSharedValidators

private val allAppSharedModules = listOf(
    moduleAppSharedApi,
    moduleAppSharedNetwork,
    moduleAppSharedRepository,
    moduleAppSharedUseCase,
    moduleAppSharedValidators,
    moduleAppSharedWorks,
    moduleAppSharedConfig
)

fun getAppSharedModules() = allAppSharedModules