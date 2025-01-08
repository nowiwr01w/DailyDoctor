package nowiwr01p.daily.doctor.encryption.di

import dev.whyoleg.cryptography.CryptographyProvider
import nowiwr01p.daily.doctor.encryption.client.EncryptionClient
import nowiwr01p.daily.doctor.encryption.server.EncryptionServer
import org.koin.dsl.module

val moduleEncryption = module {
    /**
     * CLIENT
     */
    single {
        EncryptionClient(CryptographyProvider.Default)
    }
    /**
     * SERVER
     */
    single {
        EncryptionServer(CryptographyProvider.Default)
    }
}