package pl.mosenko.songplanner.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.Single
import pl.mosenko.songplanner.data.model.PlannedSetOfSongs
import pl.mosenko.songplanner.data.model.SetOfSongs

interface SetOfSongsRepository : BaseRepository<SetOfSongs> {
    fun getSetSetOfSongs(): LiveData<List<SetOfSongs>>

    fun getPlannedSetSetOfSongs(): LiveData<List<PlannedSetOfSongs>>

    fun getSetOfSongsById(setOfSongsId: Int): LiveData<SetOfSongs>

    fun insertAll(setOfSongsList: List<SetOfSongs>): Single<List<Long>>
}