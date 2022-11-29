package cl.eme.recipe.listing.domain

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.Result
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
        Mockito.`when`(mockRepository.getRecipes()).thenReturn(Result.Right(emptyList()))

        // when
        val result: Result<Failure,List<Recipe>> = getRecipesUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Right::class.java)

        with(result as Result.Right) {
            assertThat(value).isNotNull()
            assertThat(value).isEmpty()
        }
    }

    @Test
    fun `getRecipes return a failure response`() {
        // given
        Mockito.`when`(mockRepository.getRecipes()).thenReturn(Result.Left(Failure.NetworkConnection))

        // when
        val result: Result<Failure, List<Recipe>> = getRecipesUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Left::class.java)

        with(result as Result.Left) {
            assertThat(failure).isNotNull()
            assertThat(failure).isInstanceOf(Failure.NetworkConnection::class.java)
        }
    }

    @Test
    fun `getRecipes return a successful response with 1 element`() {
        // given
        Mockito.`when`(mockRepository.getRecipes())
            .thenReturn(Result.Right(listOf(Recipe(1, "name", emptyList(), "", 90, ""))))

        // when
        val result: Result<Failure, List<Recipe>> = getRecipesUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Right::class.java)
        assertThat(result).isNotNull()

        with(result as Result.Right) {
            assertThat(value).isNotNull()
            assertThat(value).hasSize(1)
        }
    }
}