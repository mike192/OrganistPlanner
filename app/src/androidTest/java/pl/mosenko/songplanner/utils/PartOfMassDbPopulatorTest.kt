package pl.mosenko.songplanner.utils

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.InstrumentationRegistry
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest
import pl.mosenko.songplanner.data.dao.PartOfMassDao
import pl.mosenko.songplanner.roomTestModule
import pl.mosenko.songplanner.test.utilities.getBlockingValue
import java.util.*
import java.util.concurrent.Executor

class PartOfMassDbPopulatorTest : KoinTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    val partOfMassDao: PartOfMassDao by inject()

    @Before
    fun loadKoinModule() {
        loadKoinModules(roomTestModule)
    }

    @Test
    fun doWork_WhenPartOfMassesGiven_ShouldPopulateDb() {
        val partOfMassDbPopulator = PartOfMassDbPopulatorMock()
        partOfMassDbPopulator.initContext(InstrumentationRegistry.getContext())
        val resultOfWork = partOfMassDbPopulator.doWork()
        val persistedPartOfMasses = partOfMassDao.getPartOfMasses().getBlockingValue()
        assertThat(resultOfWork, `is`(Worker.Result.SUCCESS))
        assertThat(persistedPartOfMasses.size, `is`(5))
    }

    class PartOfMassDbPopulatorMock : PartOfMassDbPopulator() {
        fun initContext(context: Context) {
            internalInit(
                context, WorkerParameters(UUID.randomUUID(),
                    Data.EMPTY,
                    listOf<String>(),
                    WorkerParameters.RuntimeExtras(),
                    0,
                    Executor { runnable -> },
                    WorkerFactory { appContext, workerClassName, workerParameters -> null })
            )
        }
    }
}