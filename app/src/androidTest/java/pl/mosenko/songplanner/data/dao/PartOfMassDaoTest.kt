package pl.mosenko.songplanner.data.dao

import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import pl.mosenko.songplanner.test.utilities.getBlockingValue
import pl.mosenko.songplanner.utils.TestDataGenerator.createFakePartOfMass


@RunWith(AndroidJUnit4::class)
class PartOfMassDaoTest : DbTest() {


    @Test
    fun insert_ProperDataGiven_ShouldPersistsGivenData() {
        val partOfMass = createFakePartOfMass()
        val persistedPartOfMass = partOfMassDao.insert(partOfMass)
        assertThat(persistedPartOfMass, `is`(1L))
        assertThat(partOfMassDao.getPartOfMassById(1).getBlockingValue(), `is`(partOfMass))
    }

    @Test
    fun getPartOfMassById_WhenDataPersisted_ShouldReturnsData() {
        val partOfMass = createFakePartOfMass()
        partOfMassDao.insert(partOfMass)
        val persistedPartOfMassBy =
            partOfMassDao.getPartOfMassById(partOfMass.partOfMassId!!).getBlockingValue()
        assertThat(persistedPartOfMassBy, `is`(partOfMass))
    }

    @Test
    fun getPartOfMassById_WhenDataNotPersisted_ShouldReturnsNull() {
        val partOfMass = createFakePartOfMass()
        val peristedPartOfMassBy =
            partOfMassDao.getPartOfMassById(partOfMass.partOfMassId!!).getBlockingValue()
        assertThat(peristedPartOfMassBy, nullValue())
    }

    @Test
    fun getPartOfMasses_WhenDataPersisted_ShouldReturnsData() {
        val partOfMass1 = createFakePartOfMass()
        val partOfMass2 = createFakePartOfMass(2)
        partOfMassDao.insert(partOfMass1)
        partOfMassDao.insert(partOfMass2)
        val persistedPartOfMasses = partOfMassDao.getPartOfMasses().getBlockingValue()
        assertThat(persistedPartOfMasses, notNullValue())
        assertThat(persistedPartOfMasses.size, `is`(2))
        assertThat(persistedPartOfMasses[0], `is`(partOfMass1))
        assertThat(persistedPartOfMasses[1], `is`(partOfMass2))
    }

    @Test
    fun getPartOfMasses_WhenDataNotPersisted_ShouldReturnsEmptyList() {
        val persistedPartOfMasses = partOfMassDao.getPartOfMasses().getBlockingValue()
        assertThat(persistedPartOfMasses, `is`(emptyList()))
    }

    @Test
    fun delete_WhenDataGiven_ShouldRemoveDataFromDb() {
        val partOfMass = createFakePartOfMass()
        partOfMassDao.insert(partOfMass)
        val numberOfDeleted = partOfMassDao.delete(partOfMass)
        val persistedPartOfMass =
            partOfMassDao.getPartOfMassById(partOfMass.partOfMassId!!).getBlockingValue()
        assertThat(numberOfDeleted, `is`(1))
        assertThat(persistedPartOfMass, nullValue())
    }
}