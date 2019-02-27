package pl.mosenko.songplanner.data.songbook

import androidx.lifecycle.LiveData
import pl.mosenko.songplanner.core.repository.BaseRepository

interface SongbookRepository : BaseRepository<Songbook> {

    fun getSongbookById(songbookId: Long): LiveData<Songbook>
    fun getSongbooks(): LiveData<List<Songbook>>
}