package pl.mosenko.songplanner.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import pl.mosenko.songplanner.data.model.PartOfMass
import pl.mosenko.songplanner.utilities.getBlockingValue


@RunWith(AndroidJUnit4::class)
class PartOfMassDaoTest : DaoTest() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val partOfMassDao: PartOfMassDao by inject()

    @Test
    fun insert_ShouldPersistGivenData() {
        val partOfMass = provideFakePartOfMass()
        val partOfMassId = partOfMassDao.insert(partOfMass)
        assertThat(partOfMassId, `is`(1L))
        assertThat(partOfMassDao.getPartOfMassById(1).getBlockingValue(), `is`(partOfMass))
    }

    fun provideFakePartOfMass(partOfMassId: Long = 1, partOfMassName: String = "Wejscie") = PartOfMass(partOfMassId, partOfMassName)

    @Test
    fun getPartOfMassById_WhenDataPeristed_ShouldReturnsData() {
        val partOfMass = provideFakePartOfMass()
        partOfMassDao.insert(partOfMass)
        val partOfMassById = partOfMassDao.getPartOfMassById(partOfMass.partOfMassId).getBlockingValue()
        assertThat(partOfMassById, `is`(partOfMass))
    }

    @Test
    fun getPartOfMassById_WhenDataNotPeristed_ShouldReturnsNull() {
        val partOfMass = provideFakePartOfMass()
        val partOfMassById = partOfMassDao.getPartOfMassById(partOfMass.partOfMassId).getBlockingValue()
        assertThat(partOfMassById, nullValue())
    }

    @Test
    fun getPartOfMasses_WhenDataPersisted_ShouldReturnsData() {
        val partOfMass1 = provideFakePartOfMass()
        val partOfMass2 = provideFakePartOfMass(2)
        partOfMassDao.insert(partOfMass1)
        partOfMassDao.insert(partOfMass2)
        val partOfMasses = partOfMassDao.getPartOfMasses().getBlockingValue()
        assertThat(partOfMasses, notNullValue())
        assertThat(partOfMasses.size, `is`(2))
        assertThat(partOfMasses[0], `is`(partOfMass1))
        assertThat(partOfMasses[1], `is`(partOfMass2))
    }

    @Test
    fun getPartOfMasses_WhenDataNotPersisted_ShouldReturnsEmptyList() {
        val partOfMasses = partOfMassDao.getPartOfMasses().getBlockingValue()
        assertThat(partOfMasses, `is`(emptyList()))
    }

    @Test
    fun delete_ShouldRemoveGivenData() {
        val partOfMass = provideFakePartOfMass()
        partOfMassDao.insert(partOfMass)
        val numberOfDeleted = partOfMassDao.delete(partOfMass)
        val partOfMassById = partOfMassDao.getPartOfMassById(partOfMass.partOfMassId).getBlockingValue()
        assertThat(numberOfDeleted, `is`(1))
        assertThat(partOfMassById, nullValue())
    }
}