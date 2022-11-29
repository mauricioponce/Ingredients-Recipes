package cl.eme.recipe.core.domain

/**
 * Represent a value of one of the possible types:
 * Failure or Ok
 * In functional programming, convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 */
sealed class Result<out F, out S> {

    data class Left<out F>(val failure: F) : Result<F, Nothing>()

    data class Right<out S>(val value: S) : Result<Nothing, S>()

    val isOk get() = this is Right<S>

    val isFailure get() = this is Left<F>

    fun <F> failure(failure: F) = Left(failure)

    fun <S> ok(success: S) = Right(success)
}
