package pl.mosenko.songplanner.data.dao

import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import pl.mosenko.songplanner.test.utilities.getBlockingValue
import pl.mosenko.songplanner.utils.TestDataGenerator.createFakePartOfMass


@RunWith(AndroidJUnit4::class)
class PartOfMassDaoTest : DbTest() {

    val partOfMassDao: PartOfMassDao by inject()

    @Test
    fun insert_ShouldPersistGivenData() {
        val partOfMass = createFakePartOfMass()
        val partOfMassId = partOfMassDao.insert(partOfMass)
        assertThat(partOfMassId, `is`(1L))
        assertThat(partOfMassDao.getPartOfMassById(1).getBlockingValue(), `is`(partOfMass))
    }

    @Test
    fun getPartOfMassById_WhenDataPeristed_ShouldReturnsData() {
        val partOfMass = createFakePartOfMass()
        partOfMassDao.insert(partOfMass)
        val partOfMassById = partOfMassDao.getPartOfMassById(partOfMass.partOfMassId).getBlockingValue()
        assertThat(partOfMassById, `is`(partOfMass))
    }

    @Test
    fun getPartOfMassById_WhenDataNotPeristed_ShouldReturnsNull() {
        val partOfMass = createFakePartOfMass()
        val partOfMassById = partOfMassDao.getPartOfMassById(partOfMass.partOfMassId).getBlockingValue()
        assertThat(partOfMassById, nullValue())
    }

    @Test
    fun getPartOfMasses_WhenDataPersisted_ShouldReturnsData() {
        val partOfMass1 = createFakePartOfMass()
        val partOfMass2 = createFakePartOfMass(2)
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
        val partOfMass = createFakePartOfMass()
        partOfMassDao.insert(partOfMass)
        val numberOfDeleted = partOfMassDao.delete(partOfMass)
        val partOfMassById = partOfMassDao.getPartOfMassById(partOfMass.partOfMassId).getBlockingValue()
        assertThat(numberOfDeleted, `is`(1))
        assertThat(partOfMassById, nullValue())
    }
}