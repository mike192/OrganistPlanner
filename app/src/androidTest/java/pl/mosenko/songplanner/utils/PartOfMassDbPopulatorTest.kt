package pl.mosenko.songplanner.utils

import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.work.Data
import androidx.work.Worker
import androidx.work.impl.Extras
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.koin.standalone.inject
import pl.mosenko.songplanner.data.dao.DbTest
import pl.mosenko.songplanner.data.dao.PartOfMassDao
import pl.mosenko.songplanner.test.utilities.getBlockingValue
import java.util.*

class PartOfMassDbPopulatorTest : DbTest() {

    val partOfMassDao: PartOfMassDao by inject()

    @Test
    fun doWork_shouldPopulateDb_WhenPartOfMassesGiven() {
        val partOfMassDbPopulator = PartOfMassDbPopulatorMock()
        partOfMassDbPopulator.initContext(InstrumentationRegistry.getContext())
        val resultOfWork = partOfMassDbPopulator.doWork()
        val persistedPartOfMasses = partOfMassDao.getPartOfMasses().getBlockingValue()
        assertThat(resultOfWork, `is`(Worker.Result.SUCCESS))
        assertThat(persistedPartOfMasses.size, `is`(5))
    }

    class PartOfMassDbPopulatorMock : PartOfMassDbPopulator() {
        fun initContext(context: Context) {
            internalInit(context, UUID.randomUUID(), Extras(Data.EMPTY, Collections.emptyList(), Extras.RuntimeExtras(), -1))
        }
    }
}