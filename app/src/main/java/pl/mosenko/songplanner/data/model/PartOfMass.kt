package pl.mosenko.songplanner.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val PART_OF_MASS_TABLE = "part_of_mass"
const val PART_OF_MASS_NAME_COLUMN = "part_of_mass_name"

@Entity(tableName = PART_OF_MASS_TABLE)
class PartOfMass(@PrimaryKey(autoGenerate = true) var partOfMassId : Long,
                 @ColumnInfo(name = PART_OF_MASS_NAME_COLUMN) var partOfMassName : String)