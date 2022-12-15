package cl.eme.recipe.listing.domain

import cl.eme.recipe.assertData
import cl.eme.recipe.assertLeft
import cl.eme.recipe.assertNetworkConnectionFailure
import cl.eme.recipe.assertRight
import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.Ingredient
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
        val expectedList = emptyList<Ingredient>()
        Mockito.`when`(mockRepository.getIngredients()).thenReturn(Result.Right(expectedList))

        // when
        val result: Result<Failure, List<Ingredient>> = getIngredientsUseCase()

        // then
        result.assertRight()
        result.assertData(expectedList)
    }

    @Test
    fun `getIngredients return a failure response`() {
        // given
        Mockito.`when`(mockRepository.getIngredients()).thenReturn(Result.Left(Failure.NetworkConnection))

        // when
        val result: Result<Failure, List<Ingredient>> = getIngredientsUseCase()

        // then
        result.assertLeft()
        result.assertNetworkConnectionFailure()
    }

    @Test
    fun `getIngredients return a successful response with 1 element`() {
        // given
        val expectedList = listOf(Ingredient(1, "ingredient"))
        Mockito.`when`(mockRepository.getIngredients())
            .thenReturn(Result.Right(expectedList))

        // when
        val result: Result<Failure, List<Ingredient>> = getIngredientsUseCase()

        // then
        result.assertRight()
        result.assertData(expectedList)
    }
}