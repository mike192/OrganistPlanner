package pl.mosenko.songplanner.data.set_of_songs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import pl.mosenko.songplanner.core.db.BaseDao
import pl.mosenko.songplanner.core.db.COLUMN_ID
import pl.mosenko.songplanner.features.planned_songs.PlannedSetOfSongs

@Dao
interface SetOfSongsDao : BaseDao<SetOfSongs> {

    @Query("SELECT * FROM $SET_OF_SONGS_TABLE")
    fun getListSetOfSongs(): LiveData<List<SetOfSongs>>

    @Query("SELECT DISTINCT $SET_OF_SONGS_NAME_COLUMN FROM $SET_OF_SONGS_TABLE")
    fun getSetOfSongsNames(): LiveData<List<String>?>

    @Transaction
    @Query("SELECT * FROM $SET_OF_SONGS_TABLE") /* where $CREATED_DATE_COLUMN >= date()*/
    fun getPlannedSetSetOfSongs(): LiveData<List<PlannedSetOfSongs>>

    @Query("SELECT * FROM $SET_OF_SONGS_TABLE WHERE $COLUMN_ID = :setOfSongsId")
    fun getSetOfSongsById(setOfSongsId: Int): LiveData<SetOfSongs>

    @Insert
    fun insertAll(setOfSongsList: List<SetOfSongs>): List<Long>
}