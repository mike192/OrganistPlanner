package pl.mosenko.songplanner.data.song

import androidx.lifecycle.LiveData
import io.reactivex.Flowable
import pl.mosenko.songplanner.core.repository.BaseRepository

interface SongRepository : BaseRepository<Song> {

    fun getSongById(songId: Long): LiveData<Song>
    fun getSongs(): Flowable<List<Song>>
}