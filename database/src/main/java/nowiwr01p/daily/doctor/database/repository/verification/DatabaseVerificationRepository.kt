package nowiwr01p.daily.doctor.database.repository.verification

interface DatabaseVerificationRepository {
    suspend fun sendVerificationCode(code: String)
}