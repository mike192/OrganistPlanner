package pl.mosenko.songplanner.features.planned_songs

import androidx.room.Embedded
import androidx.room.Relation
import pl.mosenko.songplanner.core.db.COLUMN_ID
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.row.SET_OF_SONGS_ID_COLUMN
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongs

class PlannedSetOfSongs {
    @Embedded
    var setOfSongs: SetOfSongs? = null

    @Relation(
        parentColumn = COLUMN_ID,
        entityColumn = SET_OF_SONGS_ID_COLUMN,
        entity = Row::class
    )
    var rowList: List<Row> = ArrayList()
}