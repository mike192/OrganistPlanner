package pl.mosenko.songplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.SONG_TABLE
import pl.mosenko.songplanner.data.model.Song

@Dao
interface SongDao : BaseDao<Song> {
    @Query("select * from $SONG_TABLE where $COLUMN_ID = :songId")
    fun getSongById(songId: Long): LiveData<Song>
}