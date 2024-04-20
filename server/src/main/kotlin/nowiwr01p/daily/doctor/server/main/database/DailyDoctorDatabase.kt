package nowiwr01p.daily.doctor.server.main.database

import nowiwr01p.daily.doctor.server.main.database.user.UserTable
import nowiwr01p.daily.doctor.server.main.database.user.toUser
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

internal fun connectDatabase() = Database.connect(
    url = "jdbc:postgresql://localhost:5432/postgres",
    driver = "org.postgresql.Driver",
    user = "postgres",
    password = "Test1234"
)

internal fun testTransaction() = transaction {
    addLogger(StdOutSqlLogger)
    SchemaUtils.create(UserTable)
    UserTable.selectAll()
        .map { it.toUser() }
        .map { "id = ${it.id}, name = ${it.name}" }
        .forEach(::println)
}