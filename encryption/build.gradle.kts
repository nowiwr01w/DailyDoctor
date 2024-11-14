plugins {
    application
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

group = "nowiwr01p.daily.doctor.encryption.main"
version = "1.0.0"
application {
    mainClass.set("nowiwr01p.daily.doctor.encryption.main.EncryptionKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(platform("dev.whyoleg.cryptography:cryptography-bom:0.4.0"))
    implementation("dev.whyoleg.cryptography:cryptography-core")
    implementation("dev.whyoleg.cryptography:cryptography-provider-jdk")
    implementation(libs.coroutines)
    implementation(libs.kotlin.serialization.json)
    implementation(projects.modelShared)
}