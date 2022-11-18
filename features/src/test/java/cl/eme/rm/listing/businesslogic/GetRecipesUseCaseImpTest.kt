package cl.eme.rm.listing.businesslogic

import cl.eme.rm.core.domain.BResult
import cl.eme.rm.core.domain.RecipesRepository
import cl.eme.rm.core.domain.SimpleResult
import cl.eme.rm.core.domain.dto.Recipe
import com.google.common.truth.Truth
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
        Mockito.`when`(mockRepository.getRecipes()).thenReturn(BResult.Success(emptyList()))

        // when
        val result: SimpleResult<List<Recipe>> = getRecipesUseCase()

        // then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(BResult.Success::class.java)

        with(result as BResult.Success) {
            Truth.assertThat(value).isNotNull()
            Truth.assertThat(value).isEmpty()
        }
    }

    @Test
    fun `getRecipes return a failure response`() {
        // given
        Mockito.`when`(mockRepository.getRecipes()).thenReturn(BResult.Failure(Exception()))

        // when
        val result: SimpleResult<List<Recipe>> = getRecipesUseCase()

        // then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(BResult.Failure::class.java)

        with(result as BResult.Failure) {
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(error).isInstanceOf(Exception::class.java)
        }
    }

    @Test
    fun `getRecipes return a successful response with 1 element`() {
        // given
        Mockito.`when`(mockRepository.getRecipes())
            .thenReturn(BResult.Success(listOf(Recipe(1, "name", emptyList(), "", 90, ""))))

        // when
        val result: SimpleResult<List<Recipe>> = getRecipesUseCase()

        // then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(BResult.Success::class.java)
        Truth.assertThat(result).isNotNull()

        with(result as BResult.Success) {
            Truth.assertThat(value).isNotNull()
            Truth.assertThat(value).hasSize(1)
        }
    }
}