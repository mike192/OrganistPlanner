package pl.mosenko.songplanner.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.SONGBOOK_SONG_TABLE
import pl.mosenko.songplanner.data.model.SongbookSong

@Dao
interface SongbookSongDao : BaseDao<SongbookSong> {
    @Query("select * from $SONGBOOK_SONG_TABLE where $COLUMN_ID = :songbookSongId")
    fun getSongbookSongById(songbookSongId: Long): LiveData<SongbookSong>
}