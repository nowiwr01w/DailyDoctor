package nowiwr01p.daily.doctor.database

import org.jetbrains.exposed.sql.Database
import org.kodein.di.DI
import org.kodein.di.DIAware

class DailyDoctorDatabase(override val di: DI): DIAware {
    fun connect() = Database.connect(
        url = "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Test1234"
    )
}