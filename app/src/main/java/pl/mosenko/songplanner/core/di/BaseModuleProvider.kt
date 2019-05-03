package pl.mosenko.songplanner.core.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import pl.mosenko.songplanner.core.db.AppDatabase
import pl.mosenko.songplanner.core.db.DB_NAME
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassDataSource
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassRepository
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongsDataSource
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongsRepository
import pl.mosenko.songplanner.data.song.SongDataSource
import pl.mosenko.songplanner.data.song.SongRepository
import pl.mosenko.songplanner.data.songbook.SongbookDataSource
import pl.mosenko.songplanner.data.songbook.SongbookRepository
import pl.mosenko.songplanner.data.songbook_song.SongbookSongDataSource
import pl.mosenko.songplanner.data.songbook_song.SongbookSongRepository
import pl.mosenko.songplanner.features.creating_sets.CreatingSetViewModel
import pl.mosenko.songplanner.features.planned_songs.PlannedSongsViewModel

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
        single { SongDataSource(get()) as SongRepository }
        single { SongbookSongDataSource(get()) as SongbookSongRepository }
        single { SongbookDataSource(get()) as SongbookRepository }
        viewModel { CreatingSetViewModel(get(), get(), get(), get()) }
        viewModel { PlannedSongsViewModel(get()) }
    }
}

private fun ModuleDefinition.getAppDatabase(): AppDatabase {
    return Room.databaseBuilder(
        androidContext(),
        AppDatabase::class.java,
        DB_NAME
    )//TODO uncomment after increase version of WorkManager API
//        .addCallback(object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                val populatorRequest =
//                    OneTimeWorkRequestBuilder<PartOfMassDbPopulator>().build()
//                WorkManager.getInstance().enqueue(populatorRequest)
//            }
//        })
        .fallbackToDestructiveMigration() // TODO(to remove) only for testing purpose
        .build()
}