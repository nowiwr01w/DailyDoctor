package usecase.pin

import repository.pin.AppPinRepository

class AppCheckPinCodeUseCaseImpl(
    private val repository: AppPinRepository
): AppCheckPinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.checkPinCode()
    }
}