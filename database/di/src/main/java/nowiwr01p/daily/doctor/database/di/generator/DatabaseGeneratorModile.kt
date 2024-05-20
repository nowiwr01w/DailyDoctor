package nowiwr01p.daily.doctor.database.di.generator

import nowiwr01p.daily.doctor.database.data.generator.VerificationCodeGeneratorImpl
import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleDatabaseGenerator = DI.Module("DatabaseGeneratorModule") {
    /**
     * VERIFICATION CODE
     */
    bindProvider<VerificationCodeGenerator> {
        VerificationCodeGeneratorImpl()
    }
}