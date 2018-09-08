package pl.mosenko.songplanner.utils

import androidx.test.InstrumentationRegistry
import androidx.work.Worker
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.koin.standalone.get
import org.koin.standalone.inject
import org.mockito.Mockito.*
import pl.mosenko.songplanner.data.dao.DbTest
import pl.mosenko.songplanner.data.dao.PartOfMassDao
import pl.mosenko.songplanner.test.utilities.getBlockingValue

class PartOfMassDbPopulatorTest : DbTest() {

    val partOfMassDao: PartOfMassDao by inject()

    @Test
    fun doWork_shouldPopulateDb_WhenPartOfMassesGiven() {
        val partOfMassDbPopulator = PartOfMassDbPopulator()
        `when`(partOfMassDbPopulator.getApplicationContext()).thenReturn(InstrumentationRegistry.getTargetContext())
        val resultOfWork = partOfMassDbPopulator.doWork()
        val persistedPartOfMasses = partOfMassDao.getPartOfMasses().getBlockingValue()
        assertThat(resultOfWork, `is`(Worker.Result.SUCCESS))
        assertThat(persistedPartOfMasses.size, `is`(5))
    }
}