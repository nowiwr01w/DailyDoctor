package nowiwr01p.daily.doctor.database.storage.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import nowiwr01p.daily.doctor.database.table.verification.VerificationCode

interface DatabaseVerificationStorage {
    fun getVerificationCode(email: String): VerificationCode?
    fun createVerificationCode(request: SendVerificationCodeRequest): VerificationCode
    fun deleteVerificationCodes(email: String)
}