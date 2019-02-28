package pl.mosenko.songplanner.features.creating_sets

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.core.extensions.observe
import pl.mosenko.songplanner.core.platform.BaseFragment
import pl.mosenko.songplanner.databinding.FragmentCreatingSetBinding

class CreatingSetOfSongsFragment : BaseFragment() {

    private val creatingSetViewModel: CreatingSetViewModel by viewModel()
    private lateinit var fragmentCreatingSetBinding: FragmentCreatingSetBinding

    //TODO fill in SetOfSongs object with two way data binding and view model
    //make data picker for date, autocomplete for occasion and spinner for liturgical year

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCreatingSetBinding = FragmentCreatingSetBinding.inflate(inflater, container, false)
        fragmentCreatingSetBinding.viewModel = creatingSetViewModel
        fragmentCreatingSetBinding.lifecycleOwner = this
        setupRecyclerView()
        observeViewModel()
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

    private fun observeViewModel() {
        observe(creatingSetViewModel.getCreatingSetAdapterParams()) { setupAdapter(it!!) }
    }

    private fun setupAdapter(creatingSetAdapterParams: CreatingSetAdapterParams) {
        fragmentCreatingSetBinding.rowRecyclerView.adapter =
            CreatingSetAdapter(
                creatingSetAdapterParams
            )
    }

    private fun setupRecyclerView() {
        fragmentCreatingSetBinding.rowRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_creating_set, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}