package pl.mosenko.songplanner.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.mosenko.songplanner.core.utils.LocalDateTimeConverter
import pl.mosenko.songplanner.data.part_of_mass.PartOfMass
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassDao
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.row.RowDao
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongs
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongsDao
import pl.mosenko.songplanner.data.set_of_songs_creator.SetOfSongsCreatorDao
import pl.mosenko.songplanner.data.song.Song
import pl.mosenko.songplanner.data.song.SongDao
import pl.mosenko.songplanner.data.songbook.Songbook
import pl.mosenko.songplanner.data.songbook.SongbookDao
import pl.mosenko.songplanner.data.songbook_song.SongbookSong
import pl.mosenko.songplanner.data.songbook_song.SongbookSongDao

const val DB_NAME = "songs_of_mass.db"
const val COLUMN_ID = "id"
const val DB_VERSION = 1
const val NOT_EXISTED_TABLE_ID = 0L
const val SQLITE_TRUE = 1

fun isTableIdNotExist(id: Long?) : Boolean{
    return id == NOT_EXISTED_TABLE_ID
}

@Database(
    entities = arrayOf(
        PartOfMass::class,
        Row::class,
        SetOfSongs::class,
        Song::class,
        Songbook::class,
        SongbookSong::class
    ), version = DB_VERSION
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPartOfMassDao(): PartOfMassDao
    abstract fun getRowDao(): RowDao
    abstract fun getSetOfSongsDao(): SetOfSongsDao
    abstract fun getCreatorSetOfSongsDao(): SetOfSongsCreatorDao
    abstract fun getSongbookDao(): SongbookDao
    abstract fun getSongbookSongDao(): SongbookSongDao
    abstract fun getSongDao(): SongDao
}