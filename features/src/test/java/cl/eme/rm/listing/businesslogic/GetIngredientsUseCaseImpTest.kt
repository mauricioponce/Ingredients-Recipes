package cl.eme.rm.listing.businesslogic

import cl.eme.rm.core.domain.BResult
import cl.eme.rm.core.domain.RecipesRepository
import cl.eme.rm.core.domain.SimpleResult
import cl.eme.rm.core.domain.dto.Ingredient
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
        Mockito.`when`(mockRepository.getIngredients()).thenReturn(BResult.Success(emptyList()))

        // when
        val result: SimpleResult<List<Ingredient>> = getIngredientsUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(BResult.Success::class.java)

        with(result as BResult.Success) {
            assertThat(value).isNotNull()
            assertThat(value).isEmpty()
        }
    }

    @Test
    fun `getIngredients return a failure response`() {
        // given
        Mockito.`when`(mockRepository.getIngredients()).thenReturn(BResult.Failure(Exception()))

        // when
        val result: SimpleResult<List<Ingredient>> = getIngredientsUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(BResult.Failure::class.java)

        with(result as BResult.Failure) {
            assertThat(error).isNotNull()
            assertThat(error).isInstanceOf(Exception::class.java)
        }
    }

    @Test
    fun `getIngredients return a successful response with 1 element`() {
        // given
        Mockito.`when`(mockRepository.getIngredients())
            .thenReturn(BResult.Success(listOf(Ingredient(1, "ingredient"))))

        // when
        val result: SimpleResult<List<Ingredient>> = getIngredientsUseCase()

        // then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(BResult.Success::class.java)
        assertThat(result).isNotNull()

        with(result as BResult.Success) {
            assertThat(value).isNotNull()
            assertThat(value).hasSize(1)
        }
    }
}