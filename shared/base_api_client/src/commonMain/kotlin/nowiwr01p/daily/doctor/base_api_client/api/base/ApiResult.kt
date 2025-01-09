package nowiwr01p.daily.doctor.base_api_client.api.base

sealed interface ApiResult<T, E> {
    data class Success<T>(val data: T): ApiResult<T, Nothing>
    data class Error<E>(val errorData: E): ApiResult<Nothing, E>
}