import api.moduleAppSharedApi
import network.moduleAppSharedNetwork
import repository.moduleAppSharedRepository
import usecase.moduleAppSharedUseCase
import validators.moduleAppSharedValidators
import works.moduleAppSharedWorks

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