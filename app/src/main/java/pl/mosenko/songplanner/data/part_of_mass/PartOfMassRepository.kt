package pl.mosenko.songplanner.data.part_of_mass

import androidx.lifecycle.LiveData
import io.reactivex.Single
import pl.mosenko.songplanner.core.repository.BaseRepository

interface PartOfMassRepository : BaseRepository<PartOfMass> {
    fun getPartOfMasses(): LiveData<List<PartOfMass>>

    fun getBasicPartOfMasses(): LiveData<List<PartOfMass>>

    fun getPartOfMassById(partOfMassId: Long): LiveData<PartOfMass>

    fun insertAll(partOfMasses: List<PartOfMass>): Single<List<Long>>
}