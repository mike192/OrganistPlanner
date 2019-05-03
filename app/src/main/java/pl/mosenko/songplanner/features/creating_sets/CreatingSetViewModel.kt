package pl.mosenko.songplanner.features.creating_sets

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField
import pl.mosenko.songplanner.core.adapter.DropDownItem
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassRepository
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.set_of_songs.SetOfSongsRepository
import pl.mosenko.songplanner.data.song.SongRepository
import pl.mosenko.songplanner.data.songbook.SongbookRepository
import timber.log.Timber
import java.util.*

class CreatingSetViewModel(
    private val partOfMassRepository: PartOfMassRepository,
    private val songRepository: SongRepository,
    private val songbookRepository: SongbookRepository,
    private val setOfSongsRepository: SetOfSongsRepository
) : ViewModel() {

    private val dateFormatter = createDateTimeFormatter()
    val areAdapterParamsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val lectionaryCycle: MutableLiveData<String> = MutableLiveData()
    val author: MutableLiveData<String> = MutableLiveData() //TODO(11) to fill, using Google Account depending on settings
    val createdDate: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = dateFormatter.format(LocalDateTime.now())
    }
    val setOfSongName: MutableLiveData<String> = MutableLiveData()

    private fun createDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatterBuilder()
            .appendPattern(DATE_FORMAT_PATTERN)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter(Locale.getDefault())
    }

    //TODO(10) - add proper animation during extending item
    // add draggable option
    // add removing option
    fun getCreatingSetAdapterParams(): LiveData<CreatingSetAdapterParams> {
        val paramsFlowable = Flowable.fromCallable { CreatingSetAdapterParams() }
            .parallel(4)
            .runOn(Schedulers.io())
            .map { params ->
                params.preInitializedRows = partOfMassRepository.getBasicPartOfMasses()
                    .blockingFirst()
                    .mapIndexed { index, partOfMass -> Row(index.toLong(), partOfMass) }
                params
            }.map { params ->
                params.allPartOfMasses = partOfMassRepository.getPartOfMasses().blockingFirst()
                    .map { DropDownItem(it.partOfMassId!!, it.partOfMassName, it) }
                params
            }.map { params ->
                params.allSongs = songRepository.getSongs().blockingFirst()
                    .map { DropDownItem(it.songId, it.songName) }
                params
            }.map { params ->
                params.allSongbooks = songbookRepository.getSongbooks().blockingFirst()
                    .map { DropDownItem(it.songbookId, it.songbookName) }
                params
            }.sequential()
            .doOnSubscribe { areAdapterParamsLoading.postValue(true) }
            .doFinally { areAdapterParamsLoading.postValue(false) }
        return LiveDataReactiveStreams.fromPublisher(paramsFlowable)
    }

    fun getSetOfSongsNames(): LiveData<List<String>?> = setOfSongsRepository.getSetOfSongsNames()

    fun updateCreatedDate(
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int
    ) {
        createdDate.value =
            dateFormatter.format(LocalDateTime.of(year, monthOfYear, dayOfMonth, 0, 0))
    }

    fun getCreatedDateAsLocalDateTime(): LocalDateTime =
        LocalDateTime.parse(createdDate.value, dateFormatter)


    //TODO(1) think how to save all data!!!
    fun saveSetOfSongs(rowList: List<Row>) {
        Timber.d(rowList.toString())
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val DATE_FORMAT_PATTERN = "d MMMM yyyy"
    }
}
