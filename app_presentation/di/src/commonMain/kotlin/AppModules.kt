package di

import getAppSharedModules
import getLocalDatabaseModule

val appModules = getLocalDatabaseModule() + getAppSharedModules() + getAppPresentationModules()