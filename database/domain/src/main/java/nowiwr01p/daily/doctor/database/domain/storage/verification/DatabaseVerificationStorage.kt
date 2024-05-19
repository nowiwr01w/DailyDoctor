package nowiwr01p.daily.doctor.database.domain.storage.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import nowiwr01p.daily.doctor.database.tables.model.VerificationCode

interface DatabaseVerificationStorage {
    fun getVerificationCode(verificationToken: String): VerificationCode?
    fun createVerificationCode(request: SendVerificationCodeRequest): VerificationCode
    fun deleteExpiredVerificationCodes()
}