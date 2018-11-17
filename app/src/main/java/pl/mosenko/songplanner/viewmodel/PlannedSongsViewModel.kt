package pl.mosenko.songplanner.viewmodel

import androidx.lifecycle.ViewModel
import pl.mosenko.songplanner.data.repository.SetOfSongsRepository

class PlannedSongsViewModel(private val setOfSongsRepository: SetOfSongsRepository) : ViewModel() {

    fun getPlannedSetSetOfSongs() = setOfSongsRepository.getPlannedSetSetOfSongs()
}