package pl.mosenko.songplanner.data.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.SONGBOOK_TABLE
import pl.mosenko.songplanner.data.model.Songbook

@Dao
interface SongbookDao : BaseDao<Songbook> {
    @Query("select * from $SONGBOOK_TABLE where $COLUMN_ID = :songbookId")
    fun getSongbookById(songbookId: Long): Maybe<Songbook>
}