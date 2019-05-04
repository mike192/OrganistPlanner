package pl.mosenko.songplanner.data.set_of_songs_creator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.mosenko.songplanner.core.db.isTableIdNotExist
import pl.mosenko.songplanner.data.part_of_mass.PART_OF_MASS_NAME_COLUMN
import pl.mosenko.songplanner.data.part_of_mass.PART_OF_MASS_TABLE
import pl.mosenko.songplanner.data.part_of_mass.PartOfMass
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongs
import pl.mosenko.songplanner.data.song.SONG_NAME_COLUMN
import pl.mosenko.songplanner.data.song.SONG_TABLE
import pl.mosenko.songplanner.data.song.Song
import pl.mosenko.songplanner.data.songbook.SONGBOOK_NAME_COLUMN
import pl.mosenko.songplanner.data.songbook.SONGBOOK_TABLE
import pl.mosenko.songplanner.data.songbook.Songbook
import pl.mosenko.songplanner.data.songbook_song.SongbookSong

@Dao
abstract class SetOfSongsCreatorDao {

    @Insert
    fun insertCompleteSetOfSongs(setOfSongs: SetOfSongs, rowList: List<Row>) {
        val setOfSongsId = insertSetOfSongs(setOfSongs)
        rowList.forEach { row ->
            row.setOfSongsId = setOfSongsId
            createPartOfMassIfNeeded(row)
            createSongIfNeeded(row.songbookSong!!)
            createSongbookIfNeeded(row.songbookSong!!)
            createSongbookSong(row)
            insertRow(row)
        }
    }

    private fun createSongbookSong(row: Row) {
        val songbookSongId = insertSongbookSong(row.songbookSong!!)
        row.songbookSongId = songbookSongId
    }

    private fun createPartOfMassIfNeeded(row: Row) {
        if (isTableIdNotExist(row.partOfMassId)) {
            val fetchedPartOfMass = getPartOfMassByName(row.partOfMass!!.partOfMassName)
            row.partOfMassId = fetchedPartOfMass?.partOfMassId ?: insertPartOfMass(row.partOfMass!!)
        }
    }

    private fun createSongbookIfNeeded(songbookSong: SongbookSong) {
        if (isTableIdNotExist(songbookSong.songbookId)) {
            val songbook = songbookSong.songbook ?: Songbook()
            val fetchedSongbook = getSongbookByName(songbook.songbookName)
            songbookSong.songbookId = fetchedSongbook?.songbookId ?: insertSongbook(songbook)
        }
    }

    private fun createSongIfNeeded(songbookSong: SongbookSong) {
        if (isTableIdNotExist(songbookSong.songId)) {
            val fetchedSong = getSongByName(songbookSong.song!!.songName)
            songbookSong.songId = fetchedSong?.songId ?: insertSong(songbookSong.song!!)
        }
    }

    @Query("select * from $SONG_TABLE where trim($SONG_NAME_COLUMN) = :songName")
    abstract fun getSongByName(songName: String): Song?

    @Query("select * from $PART_OF_MASS_TABLE where trim($PART_OF_MASS_NAME_COLUMN) = :partOfMassName")
    abstract fun getPartOfMassByName(partOfMassName: String): PartOfMass?

    @Query("select * from $SONGBOOK_TABLE where trim($SONGBOOK_NAME_COLUMN) = :songbookName")
    abstract fun getSongbookByName(songbookName: String): Songbook?

    @Insert
    abstract fun insertSetOfSongs(setOfSongs: SetOfSongs): Long

    @Insert
    abstract fun insertRow(row: Row): Long

    @Insert
    abstract fun insertSong(song: Song): Long

    @Insert
    abstract fun insertSongbook(songbook: Songbook): Long

    @Insert
    abstract fun insertPartOfMass(partOfMass: PartOfMass): Long

    @Insert
    abstract fun insertSongbookSong(songbookSong: SongbookSong): Long
}