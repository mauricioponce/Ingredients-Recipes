package cl.eme.recipe.ready2eat

import cl.eme.recipe.assertData
import cl.eme.recipe.assertLeft
import cl.eme.recipe.assertNetworkConnectionFailure
import cl.eme.recipe.assertRight
import org.junit.After
import org.junit.Before
import org.junit.Test
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.ReadyToEat
import cl.eme.recipe.core.failure.Failure
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import java.util.*

class GetReadyToEatUseCaseImpTest : KoinTest {
    private val getReadyToEatUseCase: GetReadyToEatUseCase by inject()

    private val mockRepository: ReadyToEatRepository =
        Mockito.mock(ReadyToEatRepository::class.java)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single<GetReadyToEatUseCase> { GetReadyToEatUseCaseImp(get()) }
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
    fun `getReadyToEat return an empty list`() {
        // given
        val expectedList = emptyList<ReadyToEat>()
        Mockito.`when`(mockRepository.getReadyToEat()).thenReturn(Result.Right(expectedList))

        // when
        val result = getReadyToEatUseCase()

        // then
        result.assertRight()
        result.assertData(expectedList)
    }




    @Test
    fun `getReadyToEat return a failure response`() {
        // given
        Mockito.`when`(mockRepository.getReadyToEat())
            .thenReturn(Result.Left(Failure.NetworkConnection))

        // when
        val result = getReadyToEatUseCase()

        // then
        result.assertLeft()
        result.assertNetworkConnectionFailure()

    }

    @Test
    fun `getReadyToEat return a successful response with 1 element`() {
        // given
        val expectedList = getReadyToEatList()
        Mockito.`when`(mockRepository.getReadyToEat()).thenReturn(Result.Right(expectedList))

        // when
        val result = getReadyToEatUseCase()

        // then
        result.assertRight()
        result.assertData(expectedList)
    }

    private fun getReadyToEatList(): List<ReadyToEat> =
        listOf(
            ReadyToEat(
                name = "porotos",
                freezeDate = Date(),
                maxDurationInDays = 50,
                locationDescription = "1",
                1
            )
        )
}