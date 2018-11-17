package pl.mosenko.songplanner.utils

import androidx.work.Worker
import com.google.gson.Gson
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.data.dao.PartOfMassDao
import pl.mosenko.songplanner.data.model.PartOfMass
import pl.mosenko.songplanner.extensions.fromJsonFile
import timber.log.Timber

//TODO open for testing only
open class PartOfMassDbPopulator : Worker(), KoinComponent {

    private val partOfMassDao: PartOfMassDao by inject()

    override fun doWork(): Result {
        return try {
            val initialPartOfMasses: List<PartOfMass> =
                Gson().fromJsonFile<List<PartOfMass>>(applicationContext, R.raw.part_of_masses)
            partOfMassDao.insertAll(initialPartOfMasses)
            Result.SUCCESS
        } catch (e: Exception) {
            Timber.e(e)
            Result.FAILURE
        }
    }
}