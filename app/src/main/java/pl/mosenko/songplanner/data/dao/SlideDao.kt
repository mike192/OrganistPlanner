package pl.mosenko.songplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.SLIDE_TABLE
import pl.mosenko.songplanner.data.model.Slide

@Dao
interface SlideDao : BaseDao<Slide> {
    @Query("select * from $SLIDE_TABLE where $COLUMN_ID = :slideId")
    fun getSlideById(slideId: Long): LiveData<Slide>
}