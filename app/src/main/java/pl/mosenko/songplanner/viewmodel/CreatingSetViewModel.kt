package pl.mosenko.songplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.mosenko.songplanner.data.model.PartOfMass
import pl.mosenko.songplanner.data.model.Row
import pl.mosenko.songplanner.data.repository.PartOfMassRepository
import pl.mosenko.songplanner.presentation.adapter.DropDownItem
import pl.mosenko.songplanner.presentation.fragment.creating_set.CreatingSetAdapterParams

class CreatingSetViewModel(private val partOfMassRepository: PartOfMassRepository) : ViewModel() {

    val arePreinitializedRowsLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getCreatingSetAdapterParams(): LiveData<CreatingSetAdapterParams> {
        arePreinitializedRowsLoading.value = true
        return MediatorLiveData<CreatingSetAdapterParams>().apply {
            var basicPartOfMasses: List<PartOfMass>? = null
            var allPartOfMasses: List<PartOfMass>? = null

            fun update() {
                val localBasicPartOfMasses = basicPartOfMasses
                val localAllPartOfMasses = allPartOfMasses
                if (localBasicPartOfMasses != null && localAllPartOfMasses != null) {
                    arePreinitializedRowsLoading.value = false
                    this.value = CreatingSetAdapterParams(
                        localBasicPartOfMasses.mapIndexed { index, part ->
                            Row(
                                index.toLong(),
                                part
                            )
                        },
                        localAllPartOfMasses.map { part ->
                            DropDownItem(
                                part.partOfMassId!!,
                                part.partOfMassName,
                                part
                            )
                        }
                    )
                }
            }

            addSource(partOfMassRepository.getBasicPartOfMasses()) {
                basicPartOfMasses = it
                update()
            }

            addSource(partOfMassRepository.getPartOfMasses()) {
                allPartOfMasses = it
                update()
            }
        }
    }
}
