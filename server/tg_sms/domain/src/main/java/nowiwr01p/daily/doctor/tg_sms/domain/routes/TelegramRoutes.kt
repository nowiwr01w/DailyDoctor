package nowiwr01p.daily.doctor.tg_sms.domain.routes

import com.nowiwr01p.model.api.route.Route

sealed class TelegramRoutes(
    override val route: String
): Route {
    data object CheckSendAbility: TelegramRoutes("checkSendAbility")
    data object SendVerificationMessage: TelegramRoutes("sendVerificationMessage")
    data object CheckVerificationStatus: TelegramRoutes("checkVerificationStatus")
}