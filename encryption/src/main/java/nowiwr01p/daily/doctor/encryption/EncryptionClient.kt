package nowiwr01p.daily.doctor.encryption

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import kotlinx.serialization.encodeToString
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import kotlin.random.Random

class EncryptionClient(appScope: AppScope): EncryptionHelper(appScope) {

    suspend inline fun <reified T> encodeFromClientToServer(data: T): String {
        val plaintext = json.encodeToString(data).toByteArray(Charsets.UTF_8)
        val salt = Random.nextBytes(16)
        val info = "encryption:client-server".toByteArray() // TODO: Use sessionID or timeStamp
        val aesKey = transformCommonKeyToSecretAESKey(salt, info)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, aesKey)
        val iv = cipher.iv
        val ciphertext = cipher.doFinal(plaintext)

        val encryptedData = EncryptedData(
            ciphertext = encoder.encodeToString(ciphertext),
            iv = encoder.encodeToString(iv),
            salt = encoder.encodeToString(salt)
        )

        return json.encodeToString(encryptedData)
    }

    suspend inline fun <reified T> decodeOnClientFromServer(encryptedDataString: String): T {
        val encryptedData = json.decodeFromString<EncryptedData>(encryptedDataString)

        val ciphertext = decoder.decode(encryptedData.ciphertext)
        val iv = decoder.decode(encryptedData.iv)
        val salt = decoder.decode(encryptedData.salt)

        val info = "encryption:server-client".toByteArray() // TODO: Use sessionID or timeStamp
        val aesKey = transformCommonKeyToSecretAESKey(salt, info)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, aesKey, spec)
        val decryptedBytes = cipher.doFinal(ciphertext)

        val jsonString = String(decryptedBytes, Charsets.UTF_8)
        return json.decodeFromString(jsonString)
    }
}