package nowiwr01p.daily.doctor.encryption

import dev.whyoleg.cryptography.BinarySize.Companion.bits
import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.EC
import dev.whyoleg.cryptography.algorithms.ECDH
import dev.whyoleg.cryptography.algorithms.HKDF
import dev.whyoleg.cryptography.algorithms.SHA256
import dev.whyoleg.cryptography.providers.jdk.JDK
import kotlinx.io.bytestring.contentEquals
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random

suspend fun main() {
    val provider = CryptographyProvider.JDK
    val keysGeneratorAlgorithm = provider.get(ECDH)

    // Генерация ключевых пар
    val clientKeyPair = keysGeneratorAlgorithm.keyPairGenerator(EC.Curve.P256).generateKey()
    val serverKeyPair = keysGeneratorAlgorithm.keyPairGenerator(EC.Curve.P256).generateKey()

    // Генерация общих секретов
    val clientSharedSecret = serverKeyPair.publicKey.sharedSecretGenerator()
        .generateSharedSecret(clientKeyPair.privateKey)
    val serverSharedSecret = clientKeyPair.publicKey.sharedSecretGenerator()
        .generateSharedSecret(serverKeyPair.privateKey)

    // Проверка равенства общих секретов
    println("Shared secrets equal: ${clientSharedSecret.contentEquals(serverSharedSecret.toByteArray())}")

    // Генерация случайной соли
    val salt = Random.nextBytes(16) // 16 байт

    // Установка параметра info
    val info = "client to server key".toByteArray(Charsets.UTF_8)

    // Использование HKDF для извлечения симметричного ключа
    val hkdfAlgorithm = provider.get(HKDF)
    val digestAlgorithm = SHA256
    val outputSize = 256.bits // Для AES-256

    // Клиент извлекает симметричный ключ
    val clientSecretDerivation = hkdfAlgorithm.secretDerivation(
        digest = digestAlgorithm,
        outputSize = outputSize,
        salt = salt,
        info = info
    )
    val clientSymmetricKeyBytes = clientSecretDerivation.deriveSecretToByteArray(clientSharedSecret)
    val clientAesKey = SecretKeySpec(clientSymmetricKeyBytes, "AES")

    // Сервер извлекает симметричный ключ
    val serverSecretDerivation = hkdfAlgorithm.secretDerivation(
        digest = digestAlgorithm,
        outputSize = outputSize,
        salt = salt,
        info = info
    )
    val serverSymmetricKeyBytes = serverSecretDerivation.deriveSecretToByteArray(serverSharedSecret)
    val serverAesKey = SecretKeySpec(serverSymmetricKeyBytes, "AES")

    // Шифрование на клиенте
    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.ENCRYPT_MODE, clientAesKey)
    val iv = cipher.iv
    val plaintext = "Ваши данные".toByteArray()
    val encryptedData = cipher.doFinal(plaintext)

    // Клиент отправляет encryptedData, iv и salt серверу

    // Дешифрование на сервере
    val decipher = Cipher.getInstance("AES/GCM/NoPadding")
    val gcmSpec = GCMParameterSpec(128, iv)
    decipher.init(Cipher.DECRYPT_MODE, serverAesKey, gcmSpec)
    val decryptedData = decipher.doFinal(encryptedData)
    println("Дешифрованные данные на сервере: ${String(decryptedData)}")

    // Сервер обрабатывает данные и шифрует ответ
    val responseInfo = "server to client key".toByteArray(Charsets.UTF_8)

    // Сервер извлекает ключ для ответа
    val serverResponseSecretDerivation = hkdfAlgorithm.secretDerivation(
        digest = digestAlgorithm,
        outputSize = outputSize,
        salt = salt,
        info = responseInfo
    )
    val serverResponseSymmetricKeyBytes = serverResponseSecretDerivation.deriveSecretToByteArray(serverSharedSecret)
    val serverResponseAesKey = SecretKeySpec(serverResponseSymmetricKeyBytes, "AES")

    // Шифрование ответа сервером
    val serverCipher = Cipher.getInstance("AES/GCM/NoPadding")
    serverCipher.init(Cipher.ENCRYPT_MODE, serverResponseAesKey)
    val serverIv = serverCipher.iv
    val serverPlaintext = "Ответ сервера".toByteArray()
    val serverEncryptedData = serverCipher.doFinal(serverPlaintext)

    // Сервер отправляет serverEncryptedData, serverIv и salt клиенту

    // Клиент извлекает ключ для ответа
    val clientResponseSecretDerivation = hkdfAlgorithm.secretDerivation(
        digest = digestAlgorithm,
        outputSize = outputSize,
        salt = salt,
        info = responseInfo
    )
    val clientResponseSymmetricKeyBytes = clientResponseSecretDerivation.deriveSecretToByteArray(clientSharedSecret)
    val clientResponseAesKey = SecretKeySpec(clientResponseSymmetricKeyBytes, "AES")

    // Дешифрование на клиенте
    val clientDecipher = Cipher.getInstance("AES/GCM/NoPadding")
    val serverGcmSpec = GCMParameterSpec(128, serverIv)
    clientDecipher.init(Cipher.DECRYPT_MODE, clientResponseAesKey, serverGcmSpec)
    val clientDecryptedData = clientDecipher.doFinal(serverEncryptedData)
    println("Дешифрованные данные на клиенте: ${String(clientDecryptedData)}")
}