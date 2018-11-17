package pl.mosenko.songplanner.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.Single
import pl.mosenko.songplanner.data.model.PartOfMass

interface PartOfMassRepository : BaseRepository<PartOfMass> {
    fun getPartOfMasses(): LiveData<List<PartOfMass>>

    fun getBasicPartOfMasses(): LiveData<List<PartOfMass>>

    fun getPartOfMassById(partOfMassId: Long): LiveData<PartOfMass>

    fun insertAll(partOfMasses: List<PartOfMass>): Single<List<Long>>
}