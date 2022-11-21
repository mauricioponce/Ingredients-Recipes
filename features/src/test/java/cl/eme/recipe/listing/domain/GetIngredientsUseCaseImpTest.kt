package cl.eme.recipe.listing.domain

import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.dto.Ingredient
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

class GetIngredientsUseCaseImpTest : KoinTest {

    private val getIngredientsUseCase: GetIngredientsUseCase by inject()

    private val mockRepository: RecipesRepository = Mockito.mock(RecipesRepository::class.java)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single<GetIngredientsUseCase> { GetIngredientsUseCaseImp(get()) }
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
    fun `getIngredients should return an empty list`() {
        // given
        Mockito.`when`(mockRepository.getIngredients()).thenReturn(Result.Success(emptyList()))

        // when
        val result: Result<List<Ingredient>> = getIngredientsUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Success::class.java)

        with(result as Result.Success) {
            assertThat(data).isNotNull()
            assertThat(data).isEmpty()
        }
    }

    @Test
    fun `getIngredients return a failure response`() {
        // given
        Mockito.`when`(mockRepository.getIngredients()).thenReturn(Result.Failure(Result.Error()))

        // when
        val result: Result<List<Ingredient>> = getIngredientsUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Failure::class.java)

        with(result as Result.Failure) {
            assertThat(cause).isNotNull()
            assertThat(cause).isInstanceOf(Result.Error::class.java)
        }
    }

    @Test
    fun `getIngredients return a successful response with 1 element`() {
        // given
        Mockito.`when`(mockRepository.getIngredients())
            .thenReturn(Result.Success(listOf(Ingredient(1, "ingredient"))))

        // when
        val result: Result<List<Ingredient>> = getIngredientsUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat(result).isNotNull()

        with(result as Result.Success) {
            assertThat(data).isNotNull()
            assertThat(data).hasSize(1)
        }
    }
}