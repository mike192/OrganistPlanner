package pl.mosenko.songplanner.utils

import pl.mosenko.songplanner.data.model.PartOfMass

object TestDataGenerator {

    fun createPartOfMassList(): List<PartOfMass> {
        return listOf(
            PartOfMass(1, "Wejście")
            , PartOfMass(2, "Przygotowanie darów")
            , PartOfMass(3, "Komunia")
            , PartOfMass(4, "Uwielbienie")
            , PartOfMass(5, "Rozesłanie")
        )
    }

    fun createFakePartOfMass(partOfMassId: Long? = 1, partOfMassName: String = "Wejscie") =
        PartOfMass(partOfMassId, partOfMassName)

}