package pl.mosenko.songplanner.features.planned_songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import org.koin.android.ext.android.inject
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.core.extensions.observe
import pl.mosenko.songplanner.core.platform.BaseFragment
import pl.mosenko.songplanner.databinding.FragmentPlannedSongsBinding

class PlannedSongsFragment : BaseFragment() {
    private lateinit var fragmentPlannedSongsBinding: FragmentPlannedSongsBinding
    private val plannedSongsViewModel: PlannedSongsViewModel by inject()

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
        //TODO to replace after adding appropriate recyclerView
        observe(plannedSongsViewModel.getPlannedSetSetOfSongs()) { plannedSongs ->
            if (plannedSongs != null && !plannedSongs.isEmpty()) {
                fragmentPlannedSongsBinding.testPlannedSongs.text =
                        plannedSongs.joinToString(separator = ", ")
            }
        }
    }

    private fun onCreateSetButtonClicked(createSetButton: View) {
        createSetButton.findNavController()
            .navigate(R.id.action_plannedSongsFragment_to_creatingSetOfSongsFragment)
    }
}