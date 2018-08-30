package pl.mosenko.songplanner.data

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

class BaseApplication : Application() {

    private val baseModule = module {
        single(createOnStart = true) {
            Room.databaseBuilder(androidContext(),
                    AppDatabase::class.java, DB_NAME)
                    .build()
        }
        single { get<AppDatabase>().getPartOfMassDao() }
        single { get<AppDatabase>().getRowDao() }
        single { get<AppDatabase>().getSetOfSongsDao() }
        single { get<AppDatabase>().getSlideDao() }
        single { get<AppDatabase>().getSongDao() }
        single { get<AppDatabase>().getSongbookDao() }
        single { get<AppDatabase>().getSongbookSongDao() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(baseModule))
    }
}