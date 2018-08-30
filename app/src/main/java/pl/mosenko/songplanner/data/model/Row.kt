package pl.mosenko.songplanner.data.model

import androidx.room.*
import pl.mosenko.songplanner.data.COLUMN_ID

const val ROW_TABLE = "row"
const val SONGBOOK_SONG_ID_COLUMN = "songbook_song_id"
const val PART_OF_MASS_ID_COLUMN = "part_of_mass_id"
const val SET_OF_SONGS_ID_COLUMN = "set_of_songs_id"
const val VERSES_NUMBERS_COLUMN = "verses_numbers"
const val ORDINAL_COLUMN = "ordinal"

@Entity(tableName = ROW_TABLE,
        foreignKeys = [ForeignKey(entity = SongbookSong::class,
                parentColumns = arrayOf(COLUMN_ID),
                childColumns = arrayOf(SONGBOOK_SONG_ID_COLUMN),
                onDelete = ForeignKey.SET_NULL,
                onUpdate = ForeignKey.CASCADE
        ), ForeignKey(entity = PartOfMass::class,
                parentColumns = arrayOf(COLUMN_ID),
                childColumns = arrayOf(PART_OF_MASS_ID_COLUMN),
                onDelete = ForeignKey.SET_NULL,
                onUpdate = ForeignKey.CASCADE
        ), ForeignKey(entity = SetOfSongs::class,
                parentColumns = arrayOf(COLUMN_ID),
                childColumns = arrayOf(SET_OF_SONGS_ID_COLUMN),
                onDelete = ForeignKey.SET_NULL,
                onUpdate = ForeignKey.CASCADE
        )
        ],
        indices = [Index(SONGBOOK_SONG_ID_COLUMN), Index(PART_OF_MASS_ID_COLUMN), Index(SET_OF_SONGS_ID_COLUMN)])
data class Row(@PrimaryKey(autoGenerate = true)  @ColumnInfo(name = COLUMN_ID) var rowId: Long,
               @ColumnInfo(name = SONGBOOK_SONG_ID_COLUMN) var songbookSongId: Long,
               @ColumnInfo(name = PART_OF_MASS_ID_COLUMN) var partOfMassId: Long,
               @ColumnInfo(name = SET_OF_SONGS_ID_COLUMN) var setOfSongsId: Long,
               @ColumnInfo(name = VERSES_NUMBERS_COLUMN) var versesNumbers: String?,
               @ColumnInfo(name = ORDINAL_COLUMN) var ordinal: Long)

