package api.pin

import api.BaseApi
import org.kodein.di.DI

class PinApiImpl(kodein: DI): BaseApi(kodein), PinApi {

    override suspend fun createPinCode() {

    }

    override suspend fun checkPinCode() {

    }

    override suspend fun changePinCode() {

    }

    override suspend fun deletePinCode() {

    }
}