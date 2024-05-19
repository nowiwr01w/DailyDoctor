package nowiwr01p.daily.doctor.database

import nowiwr01p.daily.doctor.database.tables.table.user.UserTable
import nowiwr01p.daily.doctor.database.tables.table.verification.VerificationCodeTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.DI
import org.kodein.di.DIAware

class DailyDoctorDatabase(override val di: DI): DIAware {

    fun connect() = Database.connect(
        url = "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Test1234"
    ).also {
        createTables()
    }

    private fun createTables() = transaction {
        SchemaUtils.create(
            UserTable,
            VerificationCodeTable
        )
    }
}