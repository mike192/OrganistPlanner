package pl.mosenko.songplanner.data.songbook_song

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import pl.mosenko.songplanner.core.db.BaseDao
import pl.mosenko.songplanner.core.db.COLUMN_ID

@Dao
interface SongbookSongDao : BaseDao<SongbookSong> {
    @Query("select * from $SONGBOOK_SONG_TABLE where $COLUMN_ID = :songbookSongId")
    fun getSongbookSongById(songbookSongId: Long): LiveData<SongbookSong>
}