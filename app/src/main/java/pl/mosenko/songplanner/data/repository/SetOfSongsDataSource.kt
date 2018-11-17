package pl.mosenko.songplanner.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.Single
import pl.mosenko.songplanner.data.dao.SetOfSongsDao
import pl.mosenko.songplanner.data.model.PlannedSetOfSongs
import pl.mosenko.songplanner.data.model.SetOfSongs

class SetOfSongsDataSource(private val setOfSongsDao: SetOfSongsDao) : SetOfSongsRepository {

    override fun getSetSetOfSongs(): LiveData<List<SetOfSongs>> = setOfSongsDao.getSetSetOfSongs()

    override fun getPlannedSetSetOfSongs(): LiveData<List<PlannedSetOfSongs>> =
        setOfSongsDao.getPlannedSetSetOfSongs()

    override fun getSetOfSongsById(setOfSongsId: Int): LiveData<SetOfSongs> =
        setOfSongsDao.getSetOfSongsById(setOfSongsId)

    override fun insertAll(setOfSongsList: List<SetOfSongs>): Single<List<Long>> =
        Single.fromCallable { setOfSongsDao.insertAll(setOfSongsList) }

    override fun insert(entity: SetOfSongs): Single<Long> =
        Single.fromCallable { setOfSongsDao.insert(entity) }

    override fun delete(entity: SetOfSongs): Single<Int> =
        Single.fromCallable { setOfSongsDao.delete(entity) }
}