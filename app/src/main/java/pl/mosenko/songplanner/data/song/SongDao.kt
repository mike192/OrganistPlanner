package pl.mosenko.songplanner.data.song

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable
import pl.mosenko.songplanner.core.db.BaseDao
import pl.mosenko.songplanner.core.db.COLUMN_ID

@Dao
interface SongDao : BaseDao<Song> {
    @Query("select * from $SONG_TABLE where $COLUMN_ID = :songId")
    fun getSongById(songId: Long): LiveData<Song>

    @Query("select * from $SONG_TABLE")
    fun getSongs(): Flowable<List<Song>>
}