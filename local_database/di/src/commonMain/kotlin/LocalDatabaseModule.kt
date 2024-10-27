import org.koin.dsl.module

val moduleLocalDatabase = module {
    includes(
        getLocalDatabaseModule(),
        moduleLocalDatabaseData
    )
}