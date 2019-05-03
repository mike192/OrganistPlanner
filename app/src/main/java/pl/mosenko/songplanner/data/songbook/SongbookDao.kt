package pl.mosenko.songplanner.data.songbook

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable
import pl.mosenko.songplanner.core.db.BaseDao
import pl.mosenko.songplanner.core.db.COLUMN_ID

@Dao
interface SongbookDao : BaseDao<Songbook> {
    @Query("select * from $SONGBOOK_TABLE where $COLUMN_ID = :songbookId")
    fun getSongbookById(songbookId: Long): LiveData<Songbook>

    @Query("select * from $SONGBOOK_TABLE")
    fun getSongbooks() : Flowable<List<Songbook>>
}