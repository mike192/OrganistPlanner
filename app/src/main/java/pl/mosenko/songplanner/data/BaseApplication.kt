package pl.mosenko.songplanner.data

import android.app.Application
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import pl.mosenko.songplanner.BuildConfig
import pl.mosenko.songplanner.utils.PartOfMassDbPopulator
import timber.log.Timber

class BaseApplication : Application() {

    private val baseModule = module {
        single {
            Room.databaseBuilder(androidContext(),
                    AppDatabase::class.java, DB_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val populatorRequest = OneTimeWorkRequestBuilder<PartOfMassDbPopulator>().build()
                            WorkManager.getInstance().enqueue(populatorRequest)
                        }
                    })
                    .build()
        }
        single { get<AppDatabase>().getPartOfMassDao() }
        single { get<AppDatabase>().getRowDao() }
        single { get<AppDatabase>().getSetOfSongsDao() }
        single { get<AppDatabase>().getSongDao() }
        single { get<AppDatabase>().getSongbookDao() }
        single { get<AppDatabase>().getSongbookSongDao() }
    }

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        startKoin(this, listOf(baseModule))
    }

    fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}