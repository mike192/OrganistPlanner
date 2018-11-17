package pl.mosenko.songplanner.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import pl.mosenko.songplanner.data.AppDatabase
import pl.mosenko.songplanner.data.DB_NAME
import pl.mosenko.songplanner.data.repository.PartOfMassDataSource
import pl.mosenko.songplanner.data.repository.PartOfMassRepository
import pl.mosenko.songplanner.data.repository.SetOfSongsDataSource
import pl.mosenko.songplanner.data.repository.SetOfSongsRepository
import pl.mosenko.songplanner.utils.PartOfMassDbPopulator
import pl.mosenko.songplanner.viewmodel.CreatingSetViewModel
import pl.mosenko.songplanner.viewmodel.PlannedSongsViewModel

fun buildBaseModule(): Module {
    return module {
        single {
            getAppDatabase()
        }
        single { get<AppDatabase>().getPartOfMassDao() }
        single { get<AppDatabase>().getRowDao() }
        single { get<AppDatabase>().getSetOfSongsDao() }
        single { get<AppDatabase>().getSongDao() }
        single { get<AppDatabase>().getSongbookDao() }
        single { get<AppDatabase>().getSongbookSongDao() }
        single { PartOfMassDataSource(get()) as PartOfMassRepository }
        single { SetOfSongsDataSource(get()) as SetOfSongsRepository }
        viewModel { CreatingSetViewModel(get()) }
        viewModel { PlannedSongsViewModel(get()) }
    }
}

private fun ModuleDefinition.getAppDatabase(): AppDatabase {
    return Room.databaseBuilder(
        androidContext(),
        AppDatabase::class.java, DB_NAME
    )
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val populatorRequest =
                    OneTimeWorkRequestBuilder<PartOfMassDbPopulator>().build()
                WorkManager.getInstance().enqueue(populatorRequest)
            }
        })
        .fallbackToDestructiveMigration() // TODO(to remove) only for testing purpose
        .build()
}