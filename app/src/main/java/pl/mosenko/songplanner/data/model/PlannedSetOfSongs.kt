package pl.mosenko.songplanner.data.model

import androidx.room.Embedded
import androidx.room.Relation
import pl.mosenko.songplanner.data.COLUMN_ID

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