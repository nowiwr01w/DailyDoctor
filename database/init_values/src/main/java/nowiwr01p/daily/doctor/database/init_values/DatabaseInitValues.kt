package nowiwr01p.daily.doctor.database.init_values

import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nowiwr01p.daily.doctor.database.init_values.domain.brand.DatabaseInitBrandsTableUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DatabaseInitValues: KoinComponent {

    private val databaseInitBrandsTableUseCase by inject<DatabaseInitBrandsTableUseCase>()

    @OptIn(DelicateCoroutinesApi::class)
    fun init() = GlobalScope.launch {
        initBrands()
    }

    private suspend fun initBrands() {
        databaseInitBrandsTableUseCase.execute()
    }
}