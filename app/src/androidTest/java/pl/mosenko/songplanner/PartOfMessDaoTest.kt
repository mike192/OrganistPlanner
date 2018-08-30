package pl.mosenko.songplanner

import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import pl.mosenko.songplanner.data.AppDatabase
import pl.mosenko.songplanner.data.dao.PartOfMassDao
import pl.mosenko.songplanner.data.model.PartOfMass

@RunWith(AndroidJUnit4::class)
class PartOfMessDaoTest : KoinTest {
    val appDatabase: AppDatabase by inject()
    val partOfMassDao: PartOfMassDao by inject()

    @Before
    fun beforeTesting() {
        loadKoinModules(roomTestModule)
    }

    @After
    fun afterTesting() {
        stopKoin()
    }

    @Test
    fun insert_ShouldPersistGivenData() {
        val partOfMass = PartOfMass(1, "Wejscie")
        partOfMassDao.insert(partOfMass)
        Assert.assertEquals(partOfMassDao.getPartOfMassById(1), partOfMass)
    }

}