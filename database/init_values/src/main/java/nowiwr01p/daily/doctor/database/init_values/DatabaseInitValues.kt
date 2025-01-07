package nowiwr01p.daily.doctor.database.init_values

import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.DatabaseInitOnboardingTableUseCase
import nowiwr01p.daily.doctor.database.init_values.domain.subscription.DatabaseInitSubscriptionPlansTableUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DatabaseInitValues: KoinComponent {

    private val initOnboardingTableUseCase by inject<DatabaseInitOnboardingTableUseCase>()
    private val initSubscriptionPlansTableUseCase by inject<DatabaseInitSubscriptionPlansTableUseCase>()

    @OptIn(DelicateCoroutinesApi::class)
    fun init() = GlobalScope.launch {
        initOnboardings()
        initSubscriptions()
    }

    private suspend fun initOnboardings() {
        initOnboardingTableUseCase.execute()
    }

    private suspend fun initSubscriptions() {
        initSubscriptionPlansTableUseCase.execute()
    }
}