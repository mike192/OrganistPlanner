package pl.mosenko.songplanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mosenko.songplanner.data.COLUMN_ID
import java.util.*

const val SET_OF_SONGS_TABLE = "set_of_songs"
const val SET_OF_SONGS_NAME_COLUMN = "set_of_songs_name"
const val LITURGICAL_YEAR_COLUMN = "liturgical_year"
const val CREATED_DATE_COLUMN = "created_date"
const val AUTHOR_COLUMN = "author"
const val PLACE_COLUMN = "place"

@Entity(tableName = SET_OF_SONGS_TABLE)
data class SetOfSongs(@PrimaryKey(autoGenerate = true)  @ColumnInfo(name = COLUMN_ID) var setOfSongsId: Long,
                      @ColumnInfo(name = SET_OF_SONGS_NAME_COLUMN) var setOfSongsName: String,
                      @ColumnInfo(name = LITURGICAL_YEAR_COLUMN) var liturgicalYear: String,
                      @ColumnInfo(name = CREATED_DATE_COLUMN) var createdDate: Date,
                      @ColumnInfo(name = AUTHOR_COLUMN) var author: String?,
                      @ColumnInfo(name = PLACE_COLUMN) var place: String?
)
