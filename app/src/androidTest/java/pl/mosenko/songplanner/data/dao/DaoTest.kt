package pl.mosenko.songplanner.data.dao

import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import pl.mosenko.songplanner.data.AppDatabase
import pl.mosenko.songplanner.roomTestModule

abstract class DaoTest : KoinTest {
    val appDatabase: AppDatabase by inject()

    companion object {
        lateinit var dbInstance: AppDatabase

        @BeforeClass
        @JvmStatic
        fun loadDb() {
            StandAloneContext.loadKoinModules(roomTestModule)
        }

        @JvmStatic
        @AfterClass
        fun closeDb() {
            dbInstance.close()
            StandAloneContext.stopKoin()
        }
    }

    @Before
    fun openDb() {
        dbInstance = appDatabase
    }

    @After
    fun clearDb() {
        appDatabase.clearAllTables()
    }
}