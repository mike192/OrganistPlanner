package pl.mosenko.songplanner.presentation.fragment.creating_set

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.databinding.FragmentCreatingSetBinding
import pl.mosenko.songplanner.extensions.observe
import pl.mosenko.songplanner.presentation.fragment.BaseFragment
import pl.mosenko.songplanner.viewmodel.CreatingSetViewModel

class CreatingSetOfSongsFragment : BaseFragment() {

    private val creatingSetViewModel: CreatingSetViewModel by viewModel()
    private lateinit var fragmentCreatingSetBinding: FragmentCreatingSetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCreatingSetBinding = FragmentCreatingSetBinding.inflate(inflater, container, false)
        fragmentCreatingSetBinding.viewModel = creatingSetViewModel
        fragmentCreatingSetBinding.setLifecycleOwner(this)
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
                CreatingSetAdapter(creatingSetAdapterParams)
    }

    private fun setupRecyclerView() {
        fragmentCreatingSetBinding.rowRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_creating_set, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}