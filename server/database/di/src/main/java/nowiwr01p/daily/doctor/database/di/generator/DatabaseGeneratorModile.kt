package nowiwr01p.daily.doctor.database.di.generator

import nowiwr01p.daily.doctor.database.data.generator.VerificationCodeGeneratorImpl
import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator
import org.koin.dsl.module

val moduleDatabaseGenerator = module {
    /**
     * VERIFICATION CODE
     */
    factory<VerificationCodeGenerator> {
        VerificationCodeGeneratorImpl()
    }
}