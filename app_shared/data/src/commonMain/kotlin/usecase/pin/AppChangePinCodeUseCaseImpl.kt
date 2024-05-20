package usecase.pin

import repository.pin.AppPinRepository

class AppChangePinCodeUseCaseImpl(
    private val repository: AppPinRepository
): AppChangePinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.changePinCode()
    }
}