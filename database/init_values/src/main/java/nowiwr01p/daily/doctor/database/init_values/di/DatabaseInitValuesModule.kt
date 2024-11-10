package nowiwr01p.daily.doctor.database.init_values.di

import nowiwr01p.daily.doctor.database.init_values.data.brand.DatabaseInitBrandsTableUseCaseImpl
import nowiwr01p.daily.doctor.database.init_values.domain.brand.DatabaseInitBrandsTableUseCase
import org.koin.dsl.module

val moduleDatabaseInitValues = module {
    /**
     * BRAND
     */
    factory<DatabaseInitBrandsTableUseCase> {
        DatabaseInitBrandsTableUseCaseImpl()
    }
}