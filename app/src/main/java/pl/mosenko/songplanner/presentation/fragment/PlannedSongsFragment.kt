package pl.mosenko.songplanner.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.mosenko.songplanner.databinding.FragmentPlannedSongsBinding

class PlannedSongsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val fragmentPlannedSongsBinding = FragmentPlannedSongsBinding.inflate(inflater, container, false)
        return fragmentPlannedSongsBinding.root
    }
}