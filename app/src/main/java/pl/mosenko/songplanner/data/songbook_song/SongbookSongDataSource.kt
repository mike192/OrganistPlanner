package pl.mosenko.songplanner.data.songbook_song

import androidx.lifecycle.LiveData
import io.reactivex.Single

class SongbookSongDataSource(private val songbookSongDao: SongbookSongDao) :
    SongbookSongRepository {

    override fun getSongbookSongById(songbookSongId: Long) =
        songbookSongDao.getSongbookSongById(songbookSongId)

    override fun getSongbookSongs(): LiveData<List<SongbookSong>> =
        songbookSongDao.getSongbookSongs()

    override fun insert(entity: SongbookSong) =
        Single.fromCallable { songbookSongDao.insert(entity) }

    override fun delete(entity: SongbookSong) =
        Single.fromCallable { songbookSongDao.delete(entity) }
}