package nowiwr01p.daily.doctor.database.domain.generator

interface VerificationCodeGenerator {
    fun generateVerificationCode(): String
}