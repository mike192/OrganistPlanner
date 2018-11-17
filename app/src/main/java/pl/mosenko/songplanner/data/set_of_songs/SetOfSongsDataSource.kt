package pl.mosenko.songplanner.data.set_of_songs

import androidx.lifecycle.LiveData
import io.reactivex.Single
import pl.mosenko.songplanner.features.planned_songs.PlannedSetOfSongs

class SetOfSongsDataSource(private val setOfSongsDao: SetOfSongsDao) :
    SetOfSongsRepository {

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