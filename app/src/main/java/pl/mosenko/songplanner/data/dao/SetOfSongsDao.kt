package pl.mosenko.songplanner.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.SET_OF_SONGS_TABLE
import pl.mosenko.songplanner.data.model.SetOfSongs

interface SetOfSongsDao {
    @Query("SELECT * FROM $SET_OF_SONGS_TABLE")
    fun getSetSetOfSongs(): Maybe<List<SetOfSongs>>

    @Query("SELECT * FROM $SET_OF_SONGS_TABLE WHERE $COLUMN_ID = :setOfSongsId")
    fun getSetOfSongsById(setOfSongsId: Int): Maybe<SetOfSongs>

    @Insert
    fun insert(setOfSongs: SetOfSongs): Long?

    @Insert
    fun insertAll(setOfSongsList: List<SetOfSongs>): List<Long>

    @Delete
    fun delete(setOfSongs: SetOfSongs): Int
}