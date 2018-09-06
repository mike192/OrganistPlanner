package pl.mosenko.songplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.PART_OF_MASS_TABLE
import pl.mosenko.songplanner.data.model.PartOfMass

@Dao
interface PartOfMassDao : BaseDao<PartOfMass> {
    @Query("select * from $PART_OF_MASS_TABLE order by $COLUMN_ID")
    fun getPartOfMasses(): LiveData<List<PartOfMass>>

    @Query("select * from $PART_OF_MASS_TABLE where $COLUMN_ID = :partOfMassId")
    fun getPartOfMassById(partOfMassId: Long): LiveData<PartOfMass>
}