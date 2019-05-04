package pl.mosenko.songplanner.data.set_of_songs_creator

import io.reactivex.Completable
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongs

interface SetOfSongsCreatorRepository {

    fun insertCompleteSetOfSongs(setOfSongs: SetOfSongs, rowList: List<Row>): Completable
}