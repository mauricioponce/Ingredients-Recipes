package cl.eme.recipe.applayout.listing

import cl.eme.recipe.applayout.helpers.StubFactory
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Test


class RecipeItemViewTest {

    @Test
    fun `extension function toItemView mapping`() {
        // given
        val recipe = getRecipe()

        // when
        val result = recipe.toItemView()

        with(result) {
            assertWithMessage("Recipe ID is not equal").that(this.id).isEqualTo(recipe.id)
            assertWithMessage("Recipe Name is not equal").that(this.name).isEqualTo(recipe.name)
            assertWithMessage("Recipe Protein is not equal").that(this.protein).isEqualTo(recipe.protein)
            assertWithMessage("Recipe ImgUrl is not equal").that(this.imgUrl).isEqualTo(recipe.imgUrl)
            assertWithMessage("Recipe Ingredients are not equals").that(this.ingredients).isEqualTo(recipe.ingredients)
        }
    }

    @Test
    fun `getFormatPrepTime happy case`() {
        // given
        val prepTime = 60
        val expectedPrepTime = "60"

        // when
        val result = getFormatPrepTime(prepTime)

        // then
        assertThat(result).isEqualTo(expectedPrepTime)
    }

    @Test
    fun `getFormatPrepTime for zero time`() {
        // given
        val prepTime = 0
        val expectedPrepTime = "0"

        // when
        val result = getFormatPrepTime(prepTime)

        // then
        assertThat(result).isEqualTo(expectedPrepTime)
    }

    @Test
    fun `getFormatPrepTime for negative time`() {
        // given
        val prepTime = -1
        val expectedPrepTime = "0"

        // when
        val result = getFormatPrepTime(prepTime)

        // then
        assertThat(result).isEqualTo(expectedPrepTime)
    }

    private fun getRecipe() = StubFactory().recipe
}