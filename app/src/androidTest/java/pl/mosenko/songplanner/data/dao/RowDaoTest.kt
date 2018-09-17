package pl.mosenko.songplanner.data.dao

import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import pl.mosenko.songplanner.test.utilities.getBlockingValue

@RunWith(AndroidJUnit4::class)
class RowDaoTest : DbTest() {

    @Test
    fun getFullRowsBySetOfSongs_WhenNothingPersisted_ShouldReturnsNothing() {
        val persistedFullRowsBySetOfSongs = rowDao.getFullRowsBySetOfSongs(0).getBlockingValue()
        assertThat(persistedFullRowsBySetOfSongs, `is`(emptyList()))
    }
}