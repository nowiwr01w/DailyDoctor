package usecase.pin

import repository.pin.AppPinRepository

class AppCreatePinCodeUseCaseImpl(
    private val repository: AppPinRepository
): AppCreatePinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.createPinCode()
    }
}