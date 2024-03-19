package data.di

import data.dispatchers.AppDispatchersImpl
import domain.dispatchers.AppDispatchers
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val moduleData = DI.Module("DataModule") {
    /**
     * DISPATCHERS
     */
    bindSingleton<AppDispatchers> {
        AppDispatchersImpl()
    }
}