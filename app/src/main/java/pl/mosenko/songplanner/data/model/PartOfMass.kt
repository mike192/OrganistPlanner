package pl.mosenko.songplanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mosenko.songplanner.data.COLUMN_ID

const val PART_OF_MASS_TABLE = "part_of_mass"
const val PART_OF_MASS_NAME_COLUMN = "part_of_mass_name"
const val IS_BASIC_PART_COLUMN = "is_basic_part"

@Entity(tableName = PART_OF_MASS_TABLE)
data class PartOfMass(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_ID) var partOfMassId: Long? = null,
                      @ColumnInfo(name = PART_OF_MASS_NAME_COLUMN) var partOfMassName: String,
                      @ColumnInfo(name = IS_BASIC_PART_COLUMN) var isBasicPart: Boolean? = false,
                      @ColumnInfo(name = ORDINAL_COLUMN) var ordinal: Int? = null
)