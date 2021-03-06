package pl.mosenko.songplanner.data.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mosenko.songplanner.core.db.COLUMN_ID

const val SONG_TABLE = "song"
const val SONG_NAME_COLUMN = "song_name"

@Entity(tableName = SONG_TABLE)
data class Song(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_ID) var songId: Long,
    @ColumnInfo(name = SONG_NAME_COLUMN) var songName: String
) {
    constructor(songName: String) : this(songId = 0, songName = songName)
}