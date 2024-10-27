package di

import getAppSharedModules
import moduleLocalDatabase

val appModules = moduleLocalDatabase + getAppSharedModules() + getAppPresentationModules()