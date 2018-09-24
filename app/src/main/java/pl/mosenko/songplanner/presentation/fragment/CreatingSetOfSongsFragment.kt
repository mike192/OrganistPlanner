package pl.mosenko.songplanner.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.mosenko.songplanner.databinding.FragmentCreatingSetBinding

class CreatingSetOfSongsFragment : BaseFragment() {

    private lateinit var fragmentCreatingSetBinding: FragmentCreatingSetBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentCreatingSetBinding = FragmentCreatingSetBinding.inflate(inflater, container, false)
        return fragmentCreatingSetBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}