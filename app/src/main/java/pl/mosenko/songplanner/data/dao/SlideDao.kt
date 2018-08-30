package pl.mosenko.songplanner.data.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Maybe
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.SLIDE_TABLE
import pl.mosenko.songplanner.data.model.Slide

@Dao
interface SlideDao : BaseDao<Slide> {
    @Query("select * from $SLIDE_TABLE where $COLUMN_ID = :slideId")
    fun getSlideById(slideId: Long): Maybe<Slide>
}