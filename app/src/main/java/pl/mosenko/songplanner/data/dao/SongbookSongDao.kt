package pl.mosenko.songplanner.data.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.SONG_TABLE
import pl.mosenko.songplanner.data.model.SongbookSong

@Dao
interface SongbookSongDao : BaseDao<SongbookSong> {
    @Query("select * from $SONG_TABLE where $COLUMN_ID = :songId")
    fun getSongbookSongById(songId: Long): Maybe<SongbookSong>
}