package pl.mosenko.songplanner.data.songbook_song

import androidx.lifecycle.LiveData
import pl.mosenko.songplanner.core.repository.BaseRepository

interface SongbookSongRepository : BaseRepository<SongbookSong> {

    fun getSongbookSongById(songbookSongId: Long): LiveData<SongbookSong>
    fun getSongbookSongs(): LiveData<List<SongbookSong>>
}