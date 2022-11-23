package cl.eme.recipe.listing.domain

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.Recipe
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

class GetRecipesUseCaseImpTest: KoinTest {
    private val getRecipesUseCase: GetRecipesUseCase by inject()

    private val mockRepository: RecipesRepository = Mockito.mock(RecipesRepository::class.java)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single<GetRecipesUseCase> { GetRecipesUseCaseImp(get()) }
                    single { mockRepository }
                }
            )
        }
    }

    @After
    fun tearDow() {
        stopKoin()
    }

    @Test
    fun `getRecipes should return an empty list`() {
        // given
        Mockito.`when`(mockRepository.getRecipes()).thenReturn(Result.Ok(emptyList()))

        // when
        val result: Result<List<Recipe>> = getRecipesUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Ok::class.java)

        with(result as Result.Ok) {
            assertThat(data).isNotNull()
            assertThat(data).isEmpty()
        }
    }

    @Test
    fun `getRecipes return a failure response`() {
        // given
        Mockito.`when`(mockRepository.getRecipes()).thenReturn(Result.Err.NetworkConnection)

        // when
        val result: Result<List<Recipe>> = getRecipesUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Err::class.java)
    }

    @Test
    fun `getRecipes return a successful response with 1 element`() {
        // given
        Mockito.`when`(mockRepository.getRecipes())
            .thenReturn(Result.Ok(listOf(Recipe(1, "name", emptyList(), "", 90, ""))))

        // when
        val result: Result<List<Recipe>> = getRecipesUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Ok::class.java)
        assertThat(result).isNotNull()

        with(result as Result.Ok) {
            assertThat(data).isNotNull()
            assertThat(data).hasSize(1)
        }
    }
}