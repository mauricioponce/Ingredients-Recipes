package cl.eme.recipe.applayout.listing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cl.eme.recipe.applayout.helpers.StubFactory
import cl.eme.recipe.applayout.helpers.assertNetworkConnectionFailure
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.failure.Failure
import cl.eme.recipe.listing.domain.GetRecipesUseCase
import com.google.common.truth.Truth.assertThat
import getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListingViewModelTest: KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel: ListingViewModel by inject()

    @Mock
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single { ListingViewModel(get()) }
                    single { getRecipesUseCase }
                }
            )
        }

        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDow() {
        stopKoin()
    }

    @Test
    fun `getRecipes happy case`() {
        // given
        val expectedRecipes = getRecipes()
        Mockito.`when`(getRecipesUseCase())
            .thenReturn(Result.Right(expectedRecipes))

        // when
        viewModel.getRecipes()

        // then
        val result = viewModel.recipes.getOrAwaitValue()
        assertThat(result).hasSize(expectedRecipes.size)
        assertThat(result[0]).isEqualTo(expectedRecipes[0].toItemView())
    }

    @Test
    fun `getRecipes propagate failure`() {
        // given
        Mockito.`when`(getRecipesUseCase())
            .thenReturn(Result.Left(Failure.NetworkConnection))

        // when
        viewModel.getRecipes()

        // then
        val result = viewModel.failure.getOrAwaitValue()
        result.assertNetworkConnectionFailure()
    }

    private fun getRecipes() = StubFactory().recipes
}