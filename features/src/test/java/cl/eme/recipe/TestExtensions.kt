package cl.eme.recipe

import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.failure.Failure
import com.google.common.truth.Truth

fun <F, S> Result<F, S>.assertNetworkConnectionFailure() {
    with(this as Result.Left) {
        Truth.assertThat(failure).isNotNull()
        Truth.assertThat(failure).isInstanceOf(Failure.NetworkConnection::class.java)
    }
}

fun <F, S> Result<F, S>.assertLeft() {
    Truth.assertThat(this).isNotNull()
    Truth.assertThat(this).isInstanceOf(Result.Left::class.java)
}

fun <F, S> Result<F, S>.assertData(expectedList: S) {
    with(this as Result.Right) {
        Truth.assertThat(value).isNotNull()
        Truth.assertThat(value).isEqualTo(expectedList)
    }
}

fun <F, S> Result<F, S>.assertRight() {
    Truth.assertThat(this).isNotNull()
    Truth.assertThat(this).isInstanceOf(Result.Right::class.java)
}
