package pl.mosenko.songplanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mosenko.songplanner.data.COLUMN_ID

const val SLIDE_TABLE = "slide"
const val SLIDE_NUMBER_COLUMN = "slide_number"

@Entity(tableName = SLIDE_TABLE)
data class Slide(@PrimaryKey(autoGenerate = true)  @ColumnInfo(name = COLUMN_ID) var slideId : Long,
                 @ColumnInfo(name = SLIDE_NUMBER_COLUMN) var slideNumber: String)