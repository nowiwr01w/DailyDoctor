package nowiwr01p.daily.doctor.encryption.shared

import dev.whyoleg.cryptography.BinarySize.Companion.bits
import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.AES
import dev.whyoleg.cryptography.algorithms.EC
import dev.whyoleg.cryptography.algorithms.ECDH
import dev.whyoleg.cryptography.algorithms.HKDF
import dev.whyoleg.cryptography.algorithms.SHA256
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.encode
import kotlinx.io.bytestring.encodeToByteString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

abstract class EncryptionHelper(
    private val provider: CryptographyProvider
): KoinComponent {

    val json by inject<Json>()

    private var commonSecretKeyForBoth: ByteArray? = null
    private lateinit var keyPairForThisSide: ECDH.KeyPair

    /**
     * THIS SIDE PUBLIC KEY
     */
    suspend fun initPublicKey(): String {
        keyPairForThisSide = provider.get(ECDH)
            .keyPairGenerator(EC.Curve.P256)
            .generateKey()
        return getPublicKey()
    }


    @OptIn(ExperimentalEncodingApi::class)
    fun ByteArray.toBase64(): String {
        return Base64.encode(this)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun String.decodeBase64ToByteArray(): ByteArray {
        return Base64.decode(this)
    }

    fun getPublicKey() = keyPairForThisSide.publicKey
        .encodeToByteArrayBlocking(EC.PublicKey.Format.RAW)
        .toBase64()
        .also { println("Zhopa: getPublicKey = $it") }

    /**
     * OTHER SIDE PUBLIC KEY
     */
    suspend fun setOtherSidePublicKey(publicKey: String) {
        if (publicKey.isNotEmpty()) {
            commonSecretKeyForBoth = provider.get(ECDH)
                .publicKeyDecoder(EC.Curve.P256)
                .decodeFromByteArrayBlocking(EC.PublicKey.Format.RAW, publicKey.decodeBase64ToByteArray())
                .sharedSecretGenerator()
                .generateSharedSecret(keyPairForThisSide.privateKey)
                .toByteArray()
        }
    }

    /**
     * SHARED SECRET TO DECODE OTHER SIDE'S DATA
     */
    suspend fun transformCommonKeyToSecretAESKey(salt: ByteArray, info: ByteArray): AES.Key {
        if (commonSecretKeyForBoth == null) {
            throw IllegalStateException("Shared secret is not derived.")
        }
        val secretDerivation = provider.get(HKDF).secretDerivation(
            digest = SHA256,
            outputSize = 256.bits,
            salt = salt,
            info = info
        )
        val symmetricKey = provider.get(AES.GCM)
            .keyDecoder()
            .decodeFromByteArray(
                format = AES.Key.Format.RAW,
                bytes = secretDerivation.deriveSecretToByteArray(commonSecretKeyForBoth!!)
            )
        return symmetricKey
    }
}