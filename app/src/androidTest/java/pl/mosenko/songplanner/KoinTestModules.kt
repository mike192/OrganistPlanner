package pl.mosenko.songplanner

import androidx.room.Room
import org.koin.dsl.module.module
import pl.mosenko.songplanner.data.AppDatabase

val roomTestModule = module {
    single(override = true) {
        Room.inMemoryDatabaseBuilder(get(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}