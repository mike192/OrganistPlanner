package pl.mosenko.songplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.*

@Dao
interface RowDao : BaseDao<Row> {
    @Query("select * from $ROW_TABLE where $COLUMN_ID = :rowId")
    fun getRowById(rowId: Long): LiveData<Row>

    @Query("select * from $ROW_TABLE where $SET_OF_SONGS_ID_COLUMN = :setOfSongsId order by $ORDINAL_COLUMN asc")
    fun getRowsBySetOfSongs(setOfSongsId: Long): LiveData<List<Row>>

    @Insert
    fun insertAll(row: List<Row>): List<Long>
}