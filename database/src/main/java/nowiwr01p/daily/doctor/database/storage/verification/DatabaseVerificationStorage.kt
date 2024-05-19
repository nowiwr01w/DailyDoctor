package nowiwr01p.daily.doctor.database.storage.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import nowiwr01p.daily.doctor.database.table.verification.VerificationCode

interface DatabaseVerificationStorage {
    fun getVerificationCode(verificationToken: String): VerificationCode?
    fun createVerificationCode(request: SendVerificationCodeRequest): VerificationCode
    fun deleteVerificationCodes(verificationToken: String)
}