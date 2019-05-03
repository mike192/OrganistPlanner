package pl.mosenko.songplanner.data.songbook

import io.reactivex.Single

class SongbookDataSource(private val songbookDao: SongbookDao) : SongbookRepository {
    override fun insert(entity: Songbook): Single<Long> =
        Single.fromCallable { songbookDao.insert(entity) }

    override fun delete(entity: Songbook) = Single.fromCallable { songbookDao.delete(entity) }

    override fun getSongbookById(songbookId: Long) = songbookDao.getSongbookById(songbookId)

    override fun getSongbooks() = songbookDao.getSongbooks()
}