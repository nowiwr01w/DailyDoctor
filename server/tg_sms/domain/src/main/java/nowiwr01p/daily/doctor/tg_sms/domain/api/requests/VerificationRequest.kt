package nowiwr01p.daily.doctor.tg_sms.domain.api.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerificationRequest(
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("code")
    val code: String,
    @SerialName("ttl")
    val expiredAfterSeconds: Int
)
