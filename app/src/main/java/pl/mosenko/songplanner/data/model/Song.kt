package pl.mosenko.songplanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val SONG_TABLE = "song"
const val SONG_NAME_COLUMN = "song_name"

@Entity(tableName = SONG_TABLE)
data class Song(@PrimaryKey(autoGenerate = true) var songId : Long,
                @ColumnInfo(name = SONG_NAME_COLUMN) var songName : String)