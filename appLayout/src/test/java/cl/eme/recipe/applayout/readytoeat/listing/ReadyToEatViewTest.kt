package cl.eme.recipe.applayout.readytoeat.listing

import cl.eme.recipe.core.domain.dto.ReadyToEat
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.sql.Date


class ReadyToEatViewTest {
    @Test
    fun `convert from domain to item view`() {
        // given
        val domain = getReadyToEat()
        val expectedItemView = getItemView()

        // when
        val result = domain.toItemView()

        // then
        assertThat(result).isEqualTo(expectedItemView)
    }

    private fun getReadyToEat(): ReadyToEat = ReadyToEat(
        name = "name",
        freezeDate = Date(1672103624),
        maxDurationInDays = 1,
        locationDescription = "location 1",
        quantity = 1
    )

    private fun getItemView(): ReadyToEatView = ReadyToEatView(
        name = "name",
        freezeDate = "20-01-70",
        maxDurationInDays = "1",
        locationDescription = "location 1",
        quantity = 1
    )
}