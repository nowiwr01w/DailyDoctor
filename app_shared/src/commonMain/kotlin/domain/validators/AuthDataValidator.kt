package domain.validators

import domain.repository.auth.data.errors.AuthError

/***
 * Base validator contract. Describes common generic contract to validate any objects.
 */
interface AuthDataValidator<in T> {

    /***
     * @return `null` when object is valid, `AuthError` otherwise
     */
    fun validate(value: T): AuthError?
}