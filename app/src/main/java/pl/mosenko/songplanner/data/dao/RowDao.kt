package pl.mosenko.songplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.mosenko.songplanner.data.COLUMN_ID
import pl.mosenko.songplanner.data.model.*

@Dao
interface RowDao : BaseDao<Row> {
    @Query("select * from $ROW_TABLE where $COLUMN_ID = :rowId")
    fun getRowById(rowId: Long): LiveData<Row>

    @Query("select * from $ROW_TABLE where $SET_OF_SONGS_ID_COLUMN = :setOfSongsId order by $ORDINAL_COLUMN asc")
    fun getRowsBySetOfSongs(setOfSongsId: Long): LiveData<List<Row>>

    @Query("select r.$COLUMN_ID as rowId, pom.$PART_OF_MASS_NAME_COLUMN as partOfMassName," +
            " s.$SONG_NAME_COLUMN as songName, r.$VERSES_NUMBERS_COLUMN as versesNumbers," +
            " sb.$SONGBOOK_NAME_COLUMN as songbookName, ss.$NUMBER_IN_SONGBOOK_COLUMN as numberInSongbook, " +
            " ss.$SLIDE_NUMBER_COLUMN as slideNumber, r.$ORDINAL_COLUMN as ordinal" +
            " from $SET_OF_SONGS_TABLE as sof " +
            " inner join $ROW_TABLE as r on sof.$COLUMN_ID = r.$SET_OF_SONGS_ID_COLUMN " +
            " inner join $PART_OF_MASS_TABLE as pom on r.$PART_OF_MASS_ID_COLUMN = pom.$COLUMN_ID " +
            " inner join $SONGBOOK_SONG_TABLE as ss on ss.$COLUMN_ID = r.$SONGBOOK_SONG_ID_COLUMN" +
            " inner join $SONG_TABLE as s on s.$COLUMN_ID = ss.$SONG_ID_COLUMN" +
            " left join $SONGBOOK_TABLE as sb on sb.$COLUMN_ID = ss.$SONGBOOK_ID_COLUMN" +
            " where r.$SET_OF_SONGS_ID_COLUMN = :setOfSongsId" +
            " order by r.$ORDINAL_COLUMN asc")
    fun getFullRowsBySetOfSongs(setOfSongsId: Long): LiveData<List<RowFilled>>

    @Insert
    fun insertAll(row: List<Row>): List<Long>
}