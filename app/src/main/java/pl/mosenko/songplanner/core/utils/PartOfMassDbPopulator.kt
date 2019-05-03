package pl.mosenko.songplanner.core.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.core.extensions.fromJsonFile
import pl.mosenko.songplanner.data.part_of_mass.PartOfMass
import pl.mosenko.songplanner.data.part_of_mass.PartOfMassDao
import timber.log.Timber

//TODO open for testing only
open class PartOfMassDbPopulator(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams), KoinComponent {

    private val partOfMassDao: PartOfMassDao by inject()

    override fun doWork(): Result {
        return try {
            val initialPartOfMasses: List<PartOfMass> =
                Gson().fromJsonFile<List<PartOfMass>>(applicationContext, R.raw.part_of_masses)
            partOfMassDao.insertAll(initialPartOfMasses)
            Result.success()
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure()
        }
    }
}