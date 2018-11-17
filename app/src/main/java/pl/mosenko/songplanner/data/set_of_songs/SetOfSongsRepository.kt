package pl.mosenko.songplanner.data.set_of_songs

import androidx.lifecycle.LiveData
import io.reactivex.Single
import pl.mosenko.songplanner.core.repository.BaseRepository
import pl.mosenko.songplanner.features.planned_songs.PlannedSetOfSongs

interface SetOfSongsRepository : BaseRepository<SetOfSongs> {
    fun getSetSetOfSongs(): LiveData<List<SetOfSongs>>

    fun getPlannedSetSetOfSongs(): LiveData<List<PlannedSetOfSongs>>

    fun getSetOfSongsById(setOfSongsId: Int): LiveData<SetOfSongs>

    fun insertAll(setOfSongsList: List<SetOfSongs>): Single<List<Long>>
}