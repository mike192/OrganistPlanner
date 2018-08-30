package pl.mosenko.songplanner.data.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.PART_OF_MASS_TABLE
import pl.mosenko.songplanner.data.model.PartOfMass

@Dao
interface PartOfMassDao : BaseDao<PartOfMass> {
    @Query("select * from $PART_OF_MASS_TABLE")
    fun getPartOfMasses(): Maybe<List<PartOfMass>>

    @Query("select * from $PART_OF_MASS_TABLE where $COLUMN_ID = :partOfMassId")
    fun getPartOfMassById(partOfMassId: Long): Maybe<PartOfMass>
}