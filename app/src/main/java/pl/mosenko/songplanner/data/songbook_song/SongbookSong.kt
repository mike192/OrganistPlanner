package pl.mosenko.songplanner.data.songbook_song

import androidx.room.*
import pl.mosenko.songplanner.core.db.COLUMN_ID
import pl.mosenko.songplanner.data.song.Song
import pl.mosenko.songplanner.data.songbook.Songbook

const val SONGBOOK_SONG_TABLE = "songbook_song"
const val SONG_ID_COLUMN = "song_id"
const val SONGBOOK_ID_COLUMN = "songbook_id"
const val SLIDE_NUMBER_COLUMN = "slide_number"
const val NUMBER_IN_SONGBOOK_COLUMN = "number_in_songbook"

@Entity(
    tableName = SONGBOOK_SONG_TABLE,
    foreignKeys = [ForeignKey(
        entity = Song::class,
        parentColumns = arrayOf(COLUMN_ID),
        childColumns = arrayOf(SONG_ID_COLUMN),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Songbook::class,
        parentColumns = arrayOf(COLUMN_ID),
        childColumns = arrayOf(SONGBOOK_ID_COLUMN),
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE
    )
    ], indices = [Index(SONG_ID_COLUMN), Index(SONGBOOK_ID_COLUMN)]
)
data class SongbookSong(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_ID) var songbookSongId: Long,
    @ColumnInfo(name = NUMBER_IN_SONGBOOK_COLUMN) var numberInSongbook: String,
    @ColumnInfo(name = SONG_ID_COLUMN) var songId: Long,
    @ColumnInfo(name = SONGBOOK_ID_COLUMN) var songbookId: Long?,
    @ColumnInfo(name = SLIDE_NUMBER_COLUMN) var slideNumber: String?
)