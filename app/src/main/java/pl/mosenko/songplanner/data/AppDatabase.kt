package pl.mosenko.songplanner.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
        Slide::class,
        Song::class,
        Songbook::class,
        SongbookSong::class), version = DB_VERSION)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPartOfMassDao(): PartOfMassDao
    abstract fun getRowDao(): RowDao
    abstract fun getSetOfSongsDao(): SetOfSongsDao
    abstract fun getSlideDao(): SlideDao
    abstract fun getSongbookDao(): SongbookDao
    abstract fun getSongbookSongDao(): SongbookSongDao
    abstract fun getSongDao(): SongDao
}