package pl.mosenko.songplanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val SONGBOOK_TABLE_NAME = "songbook"
const val SONGBOOK_NAME_COLUMN = "songbook_name"

@Entity(tableName = SONGBOOK_TABLE_NAME)
data class Songbook(@PrimaryKey(autoGenerate = true) var songbookId: Long,
                    @ColumnInfo(name = SONGBOOK_NAME_COLUMN) var songbookName: String)
