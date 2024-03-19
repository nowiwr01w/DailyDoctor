package domain.usecase

interface UseCase<T, R> {
    suspend fun execute(input: T): R
}

suspend inline fun <T> UseCase<Unit, T>.execute(): T = execute(Unit)