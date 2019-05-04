package pl.mosenko.songplanner.data.set_of_songs_creator

import io.reactivex.Completable
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongs

class SetOfSongsCreatorDataSource(private val setOfSongsCreatorDao: SetOfSongsCreatorDao) :
    SetOfSongsCreatorRepository {

    override fun insertCompleteSetOfSongs(setOfSongs: SetOfSongs, rowList: List<Row>): Completable =
        Completable.fromAction {
            setOfSongsCreatorDao.insertCompleteSetOfSongs(setOfSongs, rowList)
        }
}