package cl.eme.recipe.core.exception

open class Error(
    val cause: Throwable? = null
)

object NoConnection : Error()

object NotFound : Error()

object AccessDenied : Error()

object ServiceUnavailable : Error()

object Unknown : Error()
