package pl.mosenko.songplanner.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import pl.mosenko.songplanner.data.dao.*
import pl.mosenko.songplanner.data.model.*
import pl.mosenko.songplanner.utils.DateTypeConverter

const val DB_NAME = "songs_of_mass.db"
const val COLUMN_ID = "id"
const val DB_VERSION = 1

@Database(entities = arrayOf(
        PartOfMass::class,
        Row::class,
        SetOfSongs::class,
        Song::class,
        Songbook::class,
        SongbookSong::class), version = DB_VERSION)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPartOfMassDao(): PartOfMassDao
    abstract fun getRowDao(): RowDao
    abstract fun getSetOfSongsDao(): SetOfSongsDao
    abstract fun getSongbookDao(): SongbookDao
    abstract fun getSongbookSongDao(): SongbookSongDao
    abstract fun getSongDao(): SongDao
}