package pl.mosenko.songplanner.utils

import androidx.work.Worker
import com.google.gson.Gson
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import pl.mosenko.songplanner.data.dao.PartOfMassDao
import pl.mosenko.songplanner.data.model.PartOfMass
import timber.log.Timber

const val PART_OF_MASSES_FILE_NAME = "part_of_masses.json"

open class PartOfMassDbPopulator : Worker(), KoinComponent {

    private val partOfMassDao: PartOfMassDao by inject()

    override fun doWork(): Result {
        return try {
            val initialPartOfMasses: List<PartOfMass> = Gson().fromJsonFile<List<PartOfMass>>(applicationContext, PART_OF_MASSES_FILE_NAME)
            partOfMassDao.insertAll(initialPartOfMasses)
            Result.SUCCESS
        } catch (e: Exception) {
            Timber.e(e)
            Result.FAILURE
        }
    }
}