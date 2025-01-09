package nowiwr01p.daily.doctor.encryption.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class EncryptedData(
    val ciphertext: String,
    val iv: String,
    val salt: String
)