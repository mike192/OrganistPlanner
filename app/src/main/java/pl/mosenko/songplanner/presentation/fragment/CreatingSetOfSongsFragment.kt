package pl.mosenko.songplanner.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.data.model.PartOfMass
import pl.mosenko.songplanner.data.model.Row
import pl.mosenko.songplanner.databinding.FragmentCreatingSetBinding

class CreatingSetOfSongsFragment : BaseFragment() {

    private lateinit var fragmentCreatingSetBinding: FragmentCreatingSetBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentCreatingSetBinding = FragmentCreatingSetBinding.inflate(inflater, container, false)
        setupRecyclerView()
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

    private fun setupRecyclerView() {
        val partOfMassList = createPartOfMassList()
        val preinitializedRows = partOfMassList.mapIndexed { index, item ->
            Row(index.toLong(), item)
        }
        fragmentCreatingSetBinding.rowRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CreatingSetOfSongsFragment.context)
            adapter = CreatingSetAdapter(preinitializedRows, partOfMassList)
        }
    }

    fun createPartOfMassList() : List<PartOfMass> {
        return listOf(
                PartOfMass(1, "Wejście", true, 1)
                , PartOfMass(2, "Przygotowanie darów", true, 2)
                , PartOfMass(3, "Komunia", true, 3)
                , PartOfMass(4, "Uwielbienie", true, 4)
                , PartOfMass(5, "Zakończenie", true, 5))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_creating_set, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}