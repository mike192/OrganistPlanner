package pl.mosenko.songplanner.features.planned_songs

import androidx.lifecycle.ViewModel
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongsRepository

class PlannedSongsViewModel(private val setOfSongsRepository: SetOfSongsRepository) : ViewModel() {

    fun getPlannedSetSetOfSongs() = setOfSongsRepository.getPlannedSetSetOfSongs()

    fun getAllSetOfSongs() = setOfSongsRepository.getSetSetOfSongs()
}