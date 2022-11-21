package cl.eme.recipe.core.domain

interface Cause

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T>(val cause: Error) : Result<T>()

    open class Error(
        val message: String? = null,
        val cause: Throwable? = null
    )
}

