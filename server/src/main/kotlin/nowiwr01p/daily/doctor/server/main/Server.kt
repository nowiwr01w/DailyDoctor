package nowiwr01p.daily.doctor.server.main

import nowiwr01p.daily.doctor.server.main.di.serverModules
import nowiwr01p.daily.doctor.server.main.server.DailyDoctorServer

fun main() {
    DailyDoctorServer(serverModules)
}