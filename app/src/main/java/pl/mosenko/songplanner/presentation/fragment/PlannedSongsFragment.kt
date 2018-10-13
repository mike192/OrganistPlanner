package pl.mosenko.songplanner.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.data.model.PartOfMass
import pl.mosenko.songplanner.databinding.FragmentPlannedSongsBinding
import pl.mosenko.songplanner.presentation.adapter.DropDownArrayAdapter
import pl.mosenko.songplanner.presentation.adapter.DropDownItem

class PlannedSongsFragment : BaseFragment() {
    private lateinit var fragmentPlannedSongsBinding: FragmentPlannedSongsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentPlannedSongsBinding = FragmentPlannedSongsBinding.inflate(inflater, container, false)
        fragmentPlannedSongsBinding.createSetButtonListener = View.OnClickListener { onCreateSetButtonClicked(it) }
        return fragmentPlannedSongsBinding.root
    }

    private fun onCreateSetButtonClicked(createSetButton: View) {
        createSetButton.findNavController().navigate(R.id.action_plannedSongsFragment_to_creatingSetOfSongsFragment)
    }
}