package pl.mosenko.songplanner.presentation.fragment

import android.os.Bundle
import android.view.*
import pl.mosenko.songplanner.R
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

    override fun onStart() {
        super.onStart()
        enableDrawer(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_creating_set, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}