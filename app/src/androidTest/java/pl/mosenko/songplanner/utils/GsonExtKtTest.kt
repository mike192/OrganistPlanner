package pl.mosenko.songplanner.utils

import androidx.test.filters.MediumTest
import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class GsonExtKtTest {

    //TODO fix

//    @Test
//    fun fromJsonFile_WhenProperArgumentsGiven_ShouldReturnsListOfPartMasses() {
//        val appContext = InstrumentationRegistry.getContext()
//        val partOfMassListSource = TestDataGenerator.createPartOfMassList()
//        val partOfMassList =
//            Gson().fromJsonFile<List<PartOfMass>>(appContext, R.raw.part_of_masses)
//        assertThat(partOfMassListSource.size, `is`(partOfMassList.size))
//        assertThat(partOfMassListSource[0], `is`(partOfMassList[0]))
//        assertThat(partOfMassListSource[1], `is`(partOfMassList[1]))
//        assertThat(partOfMassListSource[2], `is`(partOfMassList[2]))
//        assertThat(partOfMassListSource[3], `is`(partOfMassList[3]))
//        assertThat(partOfMassListSource[4], `is`(partOfMassList[4]))
//    }

//    @Test(expected = Resources.NotFoundException::class)
//    fun fromJsonFile_WhenIncorrectResourceNameGiven_ShouldThrowsFileNotFoundException() {
//        val appContext = InstrumentationRegistry.getContext()
//        Gson().fromJsonFile<List<PartOfMass>>(appContext, R.id.about_app_nav)
//    }

//    @Test(expected = JsonSyntaxException::class)
//    fun fromJsonFile_WhenIncorrectFilenameGive_ShouldThrowException_n() {
//        val appContext = InstrumentationRegistry.getContext()
//        Gson().fromJsonFile<List<PartOfMass>>(appContext, "improper_part_of_masses.json")
//    }
}