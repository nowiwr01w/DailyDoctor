package nowiwr01p.daily.doctor.database.data.generator

import com.nowiwr01p.model.const.Symbols
import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator

class VerificationCodeGeneratorImpl: VerificationCodeGenerator {

    override fun generateVerificationCode() = Symbols.DIGITS.toList()
        .shuffled()
        .joinToString(separator = "")
        .take(6)
}