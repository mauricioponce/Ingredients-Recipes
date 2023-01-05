package cl.eme.recipe.ready2eat

import cl.eme.recipe.assertData
import cl.eme.recipe.assertRight
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.ReadyToEat
import cl.eme.recipe.core.failure.Failure
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import java.sql.Date

class NewReadyToEatUseCaseImpTest : KoinTest {
    private val newReadyToEatUseCase: NewReadyToEatUseCase by inject()

    private val mockRepository: ReadyToEatRepository =
        Mockito.mock(ReadyToEatRepository::class.java)

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    single<NewReadyToEatUseCase> { NewReadyToEatUseCaseImp(get()) }
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
    fun `newReadyToEat receive a new ready to eat and can create it`() {
        // given
        val readyToEat = getReadyToEat()
        Mockito.`when`(mockRepository.createReadyToEat(readyToEat)).thenReturn(Result.Right(readyToEat))

        // when
        val result: Result<Failure, ReadyToEat> = newReadyToEatUseCase(readyToEat)

        // then
        result.assertRight()
        result.assertData(readyToEat)
    }

    private fun getReadyToEat(): ReadyToEat = ReadyToEat(
        name = "name",
        freezeDate = Date(1672103624),
        maxDurationInDays = 1,
        locationDescription = "location 1",
        quantity = 1
    )
}