package pl.mosenko.songplanner.data.song

import io.reactivex.Single

class SongDataSource(private val songDao: SongDao) : SongRepository {
    override fun getSongById(songId: Long) = songDao.getSongById(songId)

    override fun getSongs() = songDao.getSongs()

    override fun insert(entity: Song): Single<Long> =
        Single.fromCallable { songDao.insert(entity) }

    override fun delete(entity: Song): Single<Int> =
        Single.fromCallable { songDao.delete(entity) }
}