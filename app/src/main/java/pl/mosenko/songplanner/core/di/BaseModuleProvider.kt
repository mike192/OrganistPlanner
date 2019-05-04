package pl.mosenko.songplanner.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import pl.mosenko.songplanner.core.db.AppDatabase
import pl.mosenko.songplanner.core.db.DB_NAME
import pl.mosenko.songplanner.core.utils.PartOfMassDbPopulator
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassDataSource
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassRepository
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongsDataSource
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongsRepository
import pl.mosenko.songplanner.data.set_of_songs_creator.SetOfSongsCreatorDataSource
import pl.mosenko.songplanner.data.set_of_songs_creator.SetOfSongsCreatorRepository
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
        single { WorkManager.getInstance() }
        single { getDbPopulatorRequest() }
        single { getAppDatabase(androidContext(), get(), get()) }
        single { get<AppDatabase>().getPartOfMassDao() }
        single { get<AppDatabase>().getRowDao() }
        single { get<AppDatabase>().getSetOfSongsDao() }
        single { get<AppDatabase>().getSongDao() }
        single { get<AppDatabase>().getSongbookDao() }
        single { get<AppDatabase>().getSongbookSongDao() }
        single { get<AppDatabase>().getCreatorSetOfSongsDao() }
        single { PartOfMassDataSource(get()) as PartOfMassRepository }
        single { SetOfSongsDataSource(get()) as SetOfSongsRepository }
        single { SetOfSongsCreatorDataSource(get()) as SetOfSongsCreatorRepository }
        single { SongDataSource(get()) as SongRepository }
        single { SongbookSongDataSource(get()) as SongbookSongRepository }
        single { SongbookDataSource(get()) as SongbookRepository }
        viewModel { CreatingSetViewModel(get(), get(), get(), get(), get()) }
        viewModel { PlannedSongsViewModel(get()) }
    }
}

private fun getDbPopulatorRequest(): WorkRequest =
    OneTimeWorkRequestBuilder<PartOfMassDbPopulator>().build()

private fun getAppDatabase(
    androidContext: Context,
    workManager: WorkManager,
    populatorRequest: WorkRequest
): AppDatabase {
    return Room.databaseBuilder(androidContext, AppDatabase::class.java, DB_NAME)
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                workManager.enqueue(populatorRequest)
            }
        })
        .fallbackToDestructiveMigration() // TODO(to remove) only for testing purpose
        .build()
}