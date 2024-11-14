package nowiwr01p.daily.doctor.encryption

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import dev.whyoleg.cryptography.BinarySize.Companion.bits
import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.EC
import dev.whyoleg.cryptography.algorithms.ECDH
import dev.whyoleg.cryptography.algorithms.HKDF
import dev.whyoleg.cryptography.algorithms.SHA256
import dev.whyoleg.cryptography.providers.jdk.JDK
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.Base64
import javax.crypto.spec.SecretKeySpec

abstract class EncryptionHelper(appScope: AppScope) {

    val json = Json
    val encoder = Base64.getEncoder()!!
    val decoder = Base64.getDecoder()!!
    private val provider = CryptographyProvider.JDK

    private var commonSecretKeyForBoth: ByteArray? = null
    private lateinit var keyPairForThisSide: ECDH.KeyPair

    init {
        appScope.scope.launch {
            keyPairForThisSide = provider.get(ECDH)
                .keyPairGenerator(EC.Curve.P256)
                .generateKey()
        }
    }

    suspend fun setOtherSidePublicKey(publicKey: ECDH.PublicKey) {
        commonSecretKeyForBoth = publicKey.sharedSecretGenerator()
            .generateSharedSecret(keyPairForThisSide.privateKey)
            .toByteArray()
    }

    suspend fun transformCommonKeyToSecretAESKey(salt: ByteArray, info: ByteArray): SecretKeySpec {
        if (commonSecretKeyForBoth == null) {
            throw IllegalStateException("Shared secret is not derived.")
        }
        val secretDerivation = provider.get(HKDF).secretDerivation(
            digest = SHA256,
            outputSize = 256.bits,
            salt = salt,
            info = info
        )
        val symmetricKeyBytes = secretDerivation.deriveSecretToByteArray(commonSecretKeyForBoth!!)
        return SecretKeySpec(symmetricKeyBytes, "AES")
    }

    fun getPublicKey() = keyPairForThisSide.publicKey

    @Serializable
    data class EncryptedData(
        val ciphertext: String,
        val iv: String,
        val salt: String
    )
}