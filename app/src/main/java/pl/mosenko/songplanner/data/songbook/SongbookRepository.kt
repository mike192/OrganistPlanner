package pl.mosenko.songplanner.data.songbook

import androidx.lifecycle.LiveData
import io.reactivex.Flowable
import pl.mosenko.songplanner.core.repository.BaseRepository

interface SongbookRepository : BaseRepository<Songbook> {

    fun getSongbookById(songbookId: Long): LiveData<Songbook>
    fun getSongbooks(): Flowable<List<Songbook>>
}