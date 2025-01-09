package usecase.pin

import repository.pin.AppPinRepository

class AppDeletePinCodeUseCaseImpl(
    private val repository: AppPinRepository
): AppDeletePinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.deletePinCode()
    }
}