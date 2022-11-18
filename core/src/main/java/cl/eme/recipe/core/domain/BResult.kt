package cl.eme.recipe.core.domain

/**
 * It's part of the business logic as a wrapper for handling failing request
 */
sealed class BResult<out T, out E> {

    data class Success<out T>(val value: T) : BResult<T, Nothing>()

    data class Failure<out E>(val error: E) : BResult<Nothing, E>()
}

/**
 * Build a BResult with a default exception
 */
typealias SimpleResult<T> = BResult<T, Throwable>
