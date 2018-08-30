package pl.mosenko.songplanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mosenko.songplanner.data.COLUMN_ID

const val SONGBOOK_TABLE = "songbook"
const val SONGBOOK_NAME_COLUMN = "songbook_name"

@Entity(tableName = SONGBOOK_TABLE)
data class Songbook(@PrimaryKey(autoGenerate = true)  @ColumnInfo(name = COLUMN_ID) var songbookId: Long,
                    @ColumnInfo(name = SONGBOOK_NAME_COLUMN) var songbookName: String)
