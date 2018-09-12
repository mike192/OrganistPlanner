package pl.mosenko.songplanner.utils

import androidx.test.InstrumentationRegistry
import androidx.test.filters.MediumTest
import androidx.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import pl.mosenko.songplanner.data.model.PartOfMass
import java.io.FileNotFoundException

@MediumTest
@RunWith(AndroidJUnit4::class)
class GsonExtKtTest {

    @Test
    fun fromJsonFile_shouldReturnListOfPartMasses_WhenCorrectArgumentsGiven() {
        val appContext = InstrumentationRegistry.getContext()
        val partOfMassListSource = TestDataGenerator.createPartOfMassList()
        val partOfMassList = Gson().fromJsonFile<List<PartOfMass>>(appContext, PART_OF_MASSES_FILE_NAME)
        assertThat(partOfMassListSource.size, `is`(partOfMassList.size))
        assertThat(partOfMassListSource[0], `is`(partOfMassList[0]))
        assertThat(partOfMassListSource[1], `is`(partOfMassList[1]))
        assertThat(partOfMassListSource[2], `is`(partOfMassList[2]))
        assertThat(partOfMassListSource[3], `is`(partOfMassList[3]))
        assertThat(partOfMassListSource[4], `is`(partOfMassList[4]))
    }

    @Test(expected = FileNotFoundException::class)
    fun fromJsonFile_shouldThrowFileNotFoundException_WhenIncorrectFilenameGiven() {
        val appContext = InstrumentationRegistry.getContext()
        Gson().fromJsonFile<List<PartOfMass>>(appContext, "incorrect_file_name.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun fromJsonFile_shouldThrowException_WhenIncorrectFilenameGiven() {
        val appContext = InstrumentationRegistry.getContext()
        Gson().fromJsonFile<List<PartOfMass>>(appContext, "improper_part_of_masses.json")
    }
}