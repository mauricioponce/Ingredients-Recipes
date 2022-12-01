package cl.eme.recipe.applayout.helpers

import cl.eme.recipe.core.failure.Failure
import com.google.common.truth.Truth

fun Any.assertNetworkConnectionFailure() {
    Truth.assertThat(this).isInstanceOf(Failure.NetworkConnection::class.java)
}

