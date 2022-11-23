package cl.eme.recipe.core.domain

sealed class Result<out T> {
    data class Ok<out T>(val data: T) : Result<T>()

    sealed class Err: Result<Nothing>(){
        // General errors
        object NetworkConnection : Err()

        // Superclass for feature specific errors.
        abstract class FeatureErr : Err()
    }
}
