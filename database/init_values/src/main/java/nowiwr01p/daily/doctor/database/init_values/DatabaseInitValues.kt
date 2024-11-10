package nowiwr01p.daily.doctor.database.init_values

import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nowiwr01p.daily.doctor.database.init_values.domain.brand.DatabaseInitBrandsTableUseCase
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.DatabaseInitOnboardingTableUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DatabaseInitValues: KoinComponent {

    private val initBrandsTableUseCase by inject<DatabaseInitBrandsTableUseCase>()
    private val initOnboardingTableUseCase by inject<DatabaseInitOnboardingTableUseCase>()

    @OptIn(DelicateCoroutinesApi::class)
    fun init() = GlobalScope.launch {
        initBrands()
        initOnboardings()
    }

    private suspend fun initBrands() {
        initBrandsTableUseCase.execute()
    }

    private suspend fun initOnboardings() {
        initOnboardingTableUseCase.execute()
    }
}