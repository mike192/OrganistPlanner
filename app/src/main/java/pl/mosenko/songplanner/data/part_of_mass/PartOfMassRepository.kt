package pl.mosenko.songplanner.data.part_of_mass

import androidx.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Single
import pl.mosenko.songplanner.core.repository.BaseRepository

interface PartOfMassRepository : BaseRepository<PartOfMass> {

    fun getPartOfMasses(): Flowable<List<PartOfMass>>

    fun getBasicPartOfMasses(): Flowable<List<PartOfMass>>

    fun getPartOfMassById(partOfMassId: Long): LiveData<PartOfMass>

    fun insertAll(partOfMasses: List<PartOfMass>): Single<List<Long>>
}