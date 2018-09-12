package pl.mosenko.songplanner.data.dao

import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import pl.mosenko.songplanner.test.utilities.getBlockingValue

@RunWith(AndroidJUnit4::class)
class RowDaoTest : DbTest() {
    val rowDao: RowDao by inject()

    @Test
    fun getFullRowsBySetOfSongs_ShouldReturnsNothing_WhenNothingPersisted() {
        val fullRowsBySetOfSongs = rowDao.getFullRowsBySetOfSongs(0).getBlockingValue()
        assertThat(fullRowsBySetOfSongs, `is`(emptyList()))
    }
}