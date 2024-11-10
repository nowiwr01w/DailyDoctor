package nowiwr01p.daily.doctor.database

import nowiwr01p.daily.doctor.database.init_values.DatabaseInitValues
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import nowiwr01p.daily.doctor.database.tables.table.pin.PinCodeTable
import nowiwr01p.daily.doctor.database.tables.table.user.UserTable
import nowiwr01p.daily.doctor.database.tables.table.verification.VerificationCodeTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun connectDatabase() {
    CallDoctorDatabase().connect()
}

fun initDatabase() {
    DatabaseInitValues().init()
}

private class CallDoctorDatabase {

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
            BrandTable,
            UserTable,
            VerificationCodeTable,
            PinCodeTable
        )
    }
}