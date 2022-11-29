package cl.eme.recipe.newRecipe.domain

import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.core.failure.Failure
import com.google.common.truth.Truth.assertThat
import org.junit.After

import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito

class NewRecipeUseCaseImpTest : KoinTest {

    private val newRecipeUseCase: NewRecipeUseCase by inject()

    private val mockRepository: RecipesRepository = Mockito.mock(RecipesRepository::class.java)

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    single<NewRecipeUseCase> { NewRecipeUseCaseImp(get()) }
                    single { mockRepository }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `newRecipe received a recipe and can create it `() {
        // given
        val recipe = Recipe(1, "recipe name", listOf(), "protein", 30, "")
        Mockito.`when`(mockRepository.createRecipe(recipe)).thenReturn(Result.Right(recipe))

        // when
        val result: Result<Failure, Recipe> = newRecipeUseCase(recipe)

        // Then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Right::class.java)

        with(result as Result.Right) {
            assertThat(value).isNotNull()
            assertThat(value).isEqualTo(recipe)
        }
    }
}