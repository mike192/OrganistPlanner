package pl.mosenko.songplanner.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
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