package pl.mosenko.songplanner.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.*
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import pl.mosenko.songplanner.data.AppDatabase
import pl.mosenko.songplanner.roomTestModule

abstract class DbTest : KoinTest {
    val appDatabase: AppDatabase by inject()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

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