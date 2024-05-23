package di

import com.nowiwr01p.local_database.platform.getLocalDatabaseModule
import getAppSharedModules

val appModules = getLocalDatabaseModule() + getAppSharedModules() + getAppPresentationModules()