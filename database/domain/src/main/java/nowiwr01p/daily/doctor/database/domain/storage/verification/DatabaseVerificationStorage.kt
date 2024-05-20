package nowiwr01p.daily.doctor.database.domain.storage.verification

import nowiwr01p.daily.doctor.database.tables.model.VerificationCode

interface DatabaseVerificationStorage {
    fun getVerificationCode(verificationToken: String): VerificationCode?
    fun createVerificationCode(token: String): VerificationCode
    fun deleteExpiredVerificationCodes()
}