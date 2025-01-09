package nowiwr01p.daily.doctor.database.domain.storage.verification

interface DatabaseVerificationStorage {
    fun getVerificationCode(verificationToken: String): String?
    fun createVerificationCode(token: String, code: String)
    fun deleteExpiredVerificationCodes()
}