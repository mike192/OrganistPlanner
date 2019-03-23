package pl.mosenko.songplanner.features.creating_sets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField
import pl.mosenko.songplanner.core.adapter.DropDownItem
import pl.mosenko.songplanner.data.part_of_mass.PartOfMass
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassRepository
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.song.Song
import pl.mosenko.songplanner.data.song.SongRepository
import pl.mosenko.songplanner.data.songbook.Songbook
import pl.mosenko.songplanner.data.songbook.SongbookRepository
import java.util.*

class CreatingSetViewModel(
    private val partOfMassRepository: PartOfMassRepository,
    private val songRepository: SongRepository,
    private val songbookRepository: SongbookRepository
) : ViewModel() {

    private val dateFormatter = createDateTimeFormatter()
    val arePreinitializedRowsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val lectionaryCycle: MutableLiveData<String> = MutableLiveData()
    val createdDate: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = dateFormatter.format(LocalDateTime.now())
    }

    private fun createDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatterBuilder()
            .appendPattern(DATE_FORMAT_PATTERN)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter(Locale.getDefault())
    }

    //TODO change all these source live data to rx observable
    // divide it into single live data, which should be used in adapter
    // but fetch at the same moment
    fun getCreatingSetAdapterParams(): LiveData<CreatingSetAdapterParams> {
        arePreinitializedRowsLoading.value = true
        return MediatorLiveData<CreatingSetAdapterParams>().apply {
            var basicPartOfMasses: List<PartOfMass>? = null
            var allPartOfMasses: List<PartOfMass>? = null
            var songs: List<Song>? = null
            var songbooks: List<Songbook>? = null

            fun update() {
                val localBasicPartOfMasses = basicPartOfMasses
                val localAllPartOfMasses = allPartOfMasses
                val localSongs = songs
                val localSongbooks = songbooks
                if (localBasicPartOfMasses != null && localAllPartOfMasses != null
                    && localSongs != null && localSongbooks != null
                ) {
                    arePreinitializedRowsLoading.value = false
                    this.value =
                        CreatingSetAdapterParams(
                            localBasicPartOfMasses.mapIndexed { index, part ->
                                Row(
                                    index.toLong(),
                                    part
                                )
                            },
                            localAllPartOfMasses.map { part ->
                                DropDownItem(
                                    part.partOfMassId!!,
                                    part.partOfMassName,
                                    part
                                )
                            },
                            localSongs.map { song ->
                                DropDownItem(
                                    song.songId,
                                    song.songName
                                )
                            },
                            localSongbooks.map { songbook ->
                                DropDownItem(
                                    songbook.songbookId,
                                    songbook.songbookName
                                )
                            }
                        )
                }
            }

            addSource(partOfMassRepository.getBasicPartOfMasses()) {
                basicPartOfMasses = it
                update()
            }

            addSource(partOfMassRepository.getPartOfMasses()) {
                allPartOfMasses = it
                update()
            }
            addSource(songRepository.getSongs()) {
                songs = it
                update()
            }
            addSource(songbookRepository.getSongbooks()) {
                songbooks = it
                update()
            }

        }
    }

    fun updateCreatedDate(
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int
    ) {
        createdDate.value =
            dateFormatter.format(LocalDateTime.of(year, monthOfYear, dayOfMonth, 0, 0))
    }

    fun getCreatedDateAsLocalDateTime(): LocalDateTime {
        return LocalDateTime.parse(createdDate.value, dateFormatter)
    }

    companion object {
        const val DATE_FORMAT_PATTERN = "dd MMMM yyyy"
    }
}
