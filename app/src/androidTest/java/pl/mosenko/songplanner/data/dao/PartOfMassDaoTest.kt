package pl.mosenko.songplanner.data.dao

import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import pl.mosenko.songplanner.data.model.PartOfMass

@RunWith(AndroidJUnit4::class)
class PartOfMassDaoTest : DaoTest() {
    val partOfMassDao: PartOfMassDao by inject()

    @Test
    fun insert_ShouldPersistGivenData() {
        val partOfMass = provideFakePartOfMass()
        val partOfMassId = partOfMassDao.insert(partOfMass)
        assertThat(partOfMassId, `is`(1L))
        assertThat(partOfMassDao.getPartOfMassById(1).blockingGet(), `is`(partOfMass))
    }

    @Test
    fun delete_ShouldRemoveGivenData() {
        val partOfMass = provideFakePartOfMass()
        partOfMassDao.insert(partOfMass)
        val numberOfDeleted = partOfMassDao.delete(partOfMass)
        val partOfMassById = partOfMassDao.getPartOfMassById(partOfMass.partOfMassId).blockingGet()
        assertThat(numberOfDeleted, `is`(1))
        assertThat(partOfMassById, nullValue())
    }

    fun provideFakePartOfMass(partOfMassId: Long = 1, partOfMassName: String = "Wejscie") = PartOfMass(partOfMassId, partOfMassName)

    @Test
    fun getPartOfMassById() {

    }

}