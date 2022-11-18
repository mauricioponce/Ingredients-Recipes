package cl.eme.rm.newRecipe.businesslogic

import cl.eme.rm.core.domain.BResult
import cl.eme.rm.core.domain.RecipesRepository
import cl.eme.rm.core.domain.SimpleResult
import cl.eme.rm.core.domain.dto.Recipe
import cl.eme.rm.newrecipe.businesslogic.NewRecipeUseCase
import cl.eme.rm.newrecipe.businesslogic.NewRecipeUseCaseImp
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
        Mockito.`when`(mockRepository.createRecipe(recipe)).thenReturn(BResult.Success(recipe))

        // when
        val result: SimpleResult<Recipe> = newRecipeUseCase(recipe)

        // Then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(BResult.Success::class.java)

        with(result as BResult.Success) {
            assertThat(value).isNotNull()
            assertThat(value).isEqualTo(recipe)
        }
    }
}