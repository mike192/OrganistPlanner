package pl.mosenko.songplanner.data.part_of_mass

import androidx.lifecycle.LiveData
import io.reactivex.Single

class PartOfMassDataSource(private val partOfMassDao: PartOfMassDao) :
    PartOfMassRepository {

    override fun getPartOfMasses(): LiveData<List<PartOfMass>> = partOfMassDao.getPartOfMasses()

    override fun getBasicPartOfMasses(): LiveData<List<PartOfMass>> =
        partOfMassDao.getBasicPartOfMasses()

    override fun getPartOfMassById(partOfMassId: Long): LiveData<PartOfMass> =
        partOfMassDao.getPartOfMassById(partOfMassId)

    override fun insertAll(partOfMasses: List<PartOfMass>): Single<List<Long>> =
        Single.fromCallable { partOfMassDao.insertAll(partOfMasses) }

    override fun insert(entity: PartOfMass): Single<Long> =
        Single.fromCallable { partOfMassDao.insert(entity) }

    override fun delete(entity: PartOfMass): Single<Int> =
        Single.fromCallable { partOfMassDao.delete(entity) }
}