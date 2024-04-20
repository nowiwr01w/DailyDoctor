package nowiwr01p.daily.doctor.server.main

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import nowiwr01p.daily.doctor.server.main.plugins.configureCookies
import nowiwr01p.daily.doctor.server.main.plugins.configureHeaders
import nowiwr01p.daily.doctor.server.main.plugins.configureLogging
import nowiwr01p.daily.doctor.server.main.plugins.configureRouting
import nowiwr01p.daily.doctor.server.main.plugins.configureSerialization
import nowiwr01p.daily.doctor.server.main.user.UserTable
import nowiwr01p.daily.doctor.server.main.user.toUser
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    connectDatabase()
    testTransaction()
    connectServer()
}

private fun connectServer() = embeddedServer(
    factory = Netty,
    port = 8080,
    module = Application::setApplicationModule
).start(wait = true)

private fun Application.setApplicationModule() {
    configureRouting()
    configureLogging()
    configureSerialization()
//    configureHttps()
    configureHeaders()
    configureCookies()
}

private fun connectDatabase() = Database.connect(
    url = "jdbc:postgresql://localhost:5432/postgres",
    driver = "org.postgresql.Driver",
    user = "postgres",
    password = "Test1234"
)

private fun testTransaction() = transaction {
    addLogger(StdOutSqlLogger)
    SchemaUtils.create(UserTable)
    UserTable.selectAll()
        .map { it.toUser() }
        .map { "id = ${it.id}, name = ${it.name}" }
        .forEach(::println)
}