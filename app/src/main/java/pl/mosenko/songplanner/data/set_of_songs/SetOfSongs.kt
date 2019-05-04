package pl.mosenko.songplanner.data.set_of_songs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime
import pl.mosenko.songplanner.core.db.COLUMN_ID

const val SET_OF_SONGS_TABLE = "set_of_songs"
const val SET_OF_SONGS_NAME_COLUMN = "set_of_songs_name"
const val LECTIONARY_CYCLE_COLUMN = "lectionary_cycle"
const val CREATED_DATE_COLUMN = "created_date"
const val AUTHOR_COLUMN = "author"
const val PLACE_COLUMN = "place"

@Entity(tableName = SET_OF_SONGS_TABLE)
data class SetOfSongs(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_ID) var setOfSongsId: Long = 0,
    @ColumnInfo(name = SET_OF_SONGS_NAME_COLUMN) var setOfSongsName: String,
    @ColumnInfo(name = LECTIONARY_CYCLE_COLUMN) var lectionaryCycle: String,
    @ColumnInfo(name = CREATED_DATE_COLUMN) var createdDate: LocalDateTime,
    @ColumnInfo(name = AUTHOR_COLUMN) var author: String?,
    @ColumnInfo(name = PLACE_COLUMN) var place: String? = null //TODO in the future
)
