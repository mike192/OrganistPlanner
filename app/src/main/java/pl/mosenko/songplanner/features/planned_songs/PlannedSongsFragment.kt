package pl.mosenko.songplanner.features.planned_songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import org.koin.android.ext.android.inject
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.core.extensions.observe
import pl.mosenko.songplanner.core.platform.BaseFragment
import pl.mosenko.songplanner.databinding.FragmentPlannedSongsBinding

class PlannedSongsFragment : BaseFragment() {
    private lateinit var fragmentPlannedSongsBinding: FragmentPlannedSongsBinding
    private val plannedSongsViewModel: PlannedSongsViewModel by inject()
    private val workManager: WorkManager by inject()
    private val populateDbWorkRequest: WorkRequest by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPlannedSongsBinding =
            FragmentPlannedSongsBinding.inflate(inflater, container, false)
        fragmentPlannedSongsBinding.createSetButtonListener =
            View.OnClickListener { onCreateSetButtonClicked(it) }
        observeViewModel()
        return fragmentPlannedSongsBinding.root
    }

    private fun observeViewModel() {
        //TODO(2) to replace after adding appropriate recyclerView
        observe(plannedSongsViewModel.getPlannedSetSetOfSongs()) { plannedSongs ->
            if (plannedSongs?.isNotEmpty() == true) {
                fragmentPlannedSongsBinding.emptySongsTextView.text =
                    plannedSongs.joinToString(separator = ", ")
            }
        }
        observe(workManager.getWorkInfoByIdLiveData(populateDbWorkRequest.id)) { workInfo ->
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                //TODO add a proper implementation
                Toast.makeText(context, "Dane inicjalizacyjne załadowano!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun onCreateSetButtonClicked(createSetButton: View) {
        createSetButton.findNavController()
            .navigate(R.id.action_plannedSongsFragment_to_creatingSetOfSongsFragment)
    }
}