package nowiwr01p.daily.doctor.tg_sms.domain.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerificationResponseWrap(
    @SerialName("ok")
    val isSuccess: Boolean,
    @SerialName("result")
    val data: VerificationResponse
)

@Serializable
data class VerificationResponse(
    @SerialName("request_id")
    val requestId: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("request_cost")
    val requestCost: Float,
    @SerialName("remaining_balance")
    val remainingBalance: Float? = null,
    @SerialName("delivery_status")
    val deliveryStatus: DeliveryStatus? = null,
    @SerialName("verification_status")
    val verificationStatus: VerificationStatus? = null,
    @SerialName("payload")
    val payload: String? = null
)

@Serializable
data class DeliveryStatus(
    @SerialName("status")
    val status: String,
    @SerialName("updated_at")
    val updatedAt: Long
)

@Serializable
data class VerificationStatus(
    @SerialName("status")
    val status: String,
    @SerialName("updated_at")
    val updatedAt: Long,
    @SerialName("code_entered")
    val codeEntered: String? = null
)