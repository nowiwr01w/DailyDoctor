package di

import getAppSharedModules
import nowiwr01p.daily.doctor.local_database.di.moduleLocalDatabase

val appModules = moduleLocalDatabase + getAppSharedModules() + getAppPresentationModules()