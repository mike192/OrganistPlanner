package pl.mosenko.songplanner.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.test.KoinTest
import pl.mosenko.songplanner.data.AppDatabase

abstract class DbTest : KoinTest {
    lateinit var appDatabase: AppDatabase
    lateinit var partOfMassDao: PartOfMassDao
    lateinit var rowDao: RowDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun openDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        partOfMassDao = appDatabase.getPartOfMassDao()
        rowDao = appDatabase.getRowDao()
    }

    @After
    fun clearDb() {
        appDatabase.close()
    }
}