package nowiwr01p.daily.doctor.encryption.client

import dev.whyoleg.cryptography.BinarySize.Companion.bits
import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.DelicateCryptographyApi
import dev.whyoleg.cryptography.algorithms.AES
import dev.whyoleg.cryptography.random.CryptographyRandom
import kotlinx.serialization.encodeToString
import nowiwr01p.daily.doctor.encryption.shared.EncryptionHelper
import nowiwr01p.daily.doctor.encryption.shared.data.EncryptedData
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.random.Random

@OptIn(ExperimentalEncodingApi::class, DelicateCryptographyApi::class)
class EncryptionClient(provider: CryptographyProvider) : EncryptionHelper(provider) {

    suspend inline fun <reified T> encodeFromClientToServer(data: T): String {
        val plaintext = json.encodeToString(data).encodeToByteArray()
        val salt = Random.nextBytes(16)
        val info = "encryption:client-server".encodeToByteArray()

        val aesKey = transformCommonKeyToSecretAESKey(salt, info) as AES.GCM.Key

        val iv = ByteArray(12).apply {
            CryptographyRandom.nextBytes(this)
        }

        val cipher = aesKey.cipher(tagSize = 128.bits)

        val ciphertext = cipher.encryptWithIv(iv, plaintext)

        val encryptedData = EncryptedData(
            ciphertext = Base64.encode(ciphertext),
            iv = Base64.encode(iv),
            salt = Base64.encode(salt)
        )
        return json.encodeToString(encryptedData)
    }

    suspend inline fun <reified T> decodeOnClientFromServer(encryptedDataString: String): T {
        val encryptedData = json.decodeFromString<EncryptedData>(encryptedDataString)

        val ciphertext = Base64.decode(encryptedData.ciphertext)
        val iv = Base64.decode(encryptedData.iv)
        val salt = Base64.decode(encryptedData.salt)

        val info = "encryption:server-client".encodeToByteArray()

        val aesKey = transformCommonKeyToSecretAESKey(salt, info) as AES.GCM.Key

        val cipher = aesKey.cipher(tagSize = 128.bits)

        val decryptedBytes = cipher.decryptWithIv(iv, ciphertext)

        val jsonString = decryptedBytes.decodeToString()
        return json.decodeFromString(jsonString)
    }
}