package nowiwr01p.daily.doctor.server.domain.usecase.subscription

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.usecase.UseCase
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.server.domain.usecase.subscription.ServerGetSubscriptionPlansUseCase.Params

interface ServerGetSubscriptionPlansUseCase: UseCase<Params, List<SubscriptionPlan>> {
    data class Params(
        val brand: BrandConfigType,
        val language: Language
    )
}