package pl.mosenko.songplanner.features.creating_sets

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import org.koin.android.viewmodel.ext.android.viewModel
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.core.adapter.HintArrayAdapter
import pl.mosenko.songplanner.core.extensions.observe
import pl.mosenko.songplanner.core.platform.BaseFragment
import pl.mosenko.songplanner.databinding.FragmentCreatingSetBinding


class CreatingSetOfSongsFragment : BaseFragment() {

    private val creatingSetViewModel: CreatingSetViewModel by viewModel()
    private lateinit var fragmentCreatingSetBinding: FragmentCreatingSetBinding

    //TODO fill in SetOfSongs object with two way data binding and view model
    //make data picker for date, autocomplete for occasion and --spinner-- for liturgical year

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCreatingSetBinding = FragmentCreatingSetBinding.inflate(inflater, container, false)
        fragmentCreatingSetBinding.viewModel = creatingSetViewModel
        fragmentCreatingSetBinding.dateButtonListener =
            View.OnClickListener { this.handleDateButtonClicked() }
        fragmentCreatingSetBinding.lifecycleOwner = this
        setupLectionaryCyclesSpinner()
        setupRecyclerView()
        observeViewModel()
        return fragmentCreatingSetBinding.root
    }

    private fun setupLectionaryCyclesSpinner() {
        val lectionaryCycles = listOf(
            *resources.getStringArray(pl.mosenko.songplanner.R.array.lectionary_cycles)
        )
        fragmentCreatingSetBinding.lectionaryCyclesSpinner.adapter =
            HintArrayAdapter<String>(context = context!!, objects = lectionaryCycles)
        fragmentCreatingSetBinding.lectionaryCyclesSpinner.selectedItem
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

    private var datePickerDialog: DatePickerDialog? = null

    private fun handleDateButtonClicked() {
        val createdDate = creatingSetViewModel.getCreatedDateAsLocalDateTime()
        datePickerDialog = DatePickerDialog.newInstance(
            { _, year, monthOfYear, dayOfMonth ->
                creatingSetViewModel.updateCreatedDate(
                    year,
                    monthOfYear,
                    dayOfMonth
                )
            },
            createdDate.year,
            createdDate.monthValue,
            createdDate.dayOfMonth
        )
        datePickerDialog!!.version = DatePickerDialog.Version.VERSION_2
        datePickerDialog!!.dismissOnPause(true)
        datePickerDialog!!.setOkText(R.string.button_ok)
        datePickerDialog!!.setCancelText(R.string.button_cancel)
        datePickerDialog!!.version = DatePickerDialog.Version.VERSION_2
        datePickerDialog!!.show(fragmentManager, DATE_PICKER_DIALOG_TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        with(datePickerDialog) { this?.dismiss() }
    }

    private fun setupRecyclerView() {
        fragmentCreatingSetBinding.rowRecyclerView.layoutManager = LinearLayoutManager(context)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(pl.mosenko.songplanner.R.menu.menu_creating_set, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        const val DATE_PICKER_DIALOG_TAG = "DATE_PICKER_DIALOG_TAG"
    }
}