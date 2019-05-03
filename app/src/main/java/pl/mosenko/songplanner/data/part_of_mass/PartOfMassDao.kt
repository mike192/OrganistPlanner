package pl.mosenko.songplanner.data.part_of_mass

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable
import pl.mosenko.songplanner.core.db.BaseDao
import pl.mosenko.songplanner.core.db.COLUMN_ID
import pl.mosenko.songplanner.core.db.SQLITE_TRUE
import pl.mosenko.songplanner.data.row.ORDINAL_COLUMN

@Dao
interface PartOfMassDao : BaseDao<PartOfMass> {
    @Query("select * from $PART_OF_MASS_TABLE order by $ORDINAL_COLUMN")
    fun getPartOfMasses(): Flowable<List<PartOfMass>>

    @Query("select * from $PART_OF_MASS_TABLE where $PART_OF_MASS_IS_BASIC_PART_COLUMN = $SQLITE_TRUE order by $ORDINAL_COLUMN")
    fun getBasicPartOfMasses(): Flowable<List<PartOfMass>>

    @Query("select * from $PART_OF_MASS_TABLE where $COLUMN_ID = :partOfMassId")
    fun getPartOfMassById(partOfMassId: Long): LiveData<PartOfMass>

    @Insert
    fun insertAll(partOfMasses: List<PartOfMass>): List<Long>
}