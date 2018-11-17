package pl.mosenko.songplanner.features.creating_sets

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import pl.mosenko.songplanner.core.adapter.DropDownArrayAdapter
import pl.mosenko.songplanner.core.adapter.DropDownItem
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.databinding.CreatingRowItemBinding

class CreatingSetAdapter(
    private val creatingSetAdapterParams: CreatingSetAdapterParams
) : RecyclerView.Adapter<CreatingSetAdapter.ViewHolder>() {

    private lateinit var recyclerView: RecyclerView
    private val partOfMassAdapter: DropDownArrayAdapter<Long, String> by lazy {
        DropDownArrayAdapter(
            recyclerView.context,
            R.layout.simple_dropdown_item_1line,
            creatingSetAdapterParams.allPartOfMasses
        )
    }

    var viewHolderRowList: MutableList<ViewHolderRow> = creatingSetAdapterParams.preinitializedRows
        .map {
            ViewHolderRow(
                ObservableBoolean(false),
                it
            )
        }
        .toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            CreatingRowItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            setIsRecyclable(false)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return viewHolderRowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, viewHolderRowList.get(position))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    inner class ViewHolder(private val creatingRowItemBinding: CreatingRowItemBinding) :
        RecyclerView.ViewHolder(creatingRowItemBinding.root) {

        fun bind(position: Int, viewHolderRow: ViewHolderRow) {
            creatingRowItemBinding.apply {
                row = viewHolderRow.row
                onExpandListener =
                        View.OnClickListener { onExpandClickListener(viewHolderRow) }
                partOfMassInput.setDropDownArrayAdapter(partOfMassAdapter)
                isExtendedViewVisible = viewHolderRow.isExtendedViewVisible
            }
        }

        fun onExpandClickListener(viewHolderRow: ViewHolderRow) {
            viewHolderRow.isExtendedViewVisible.set(!viewHolderRow.isExtendedViewVisible.get())
            TransitionManager.beginDelayedTransition(recyclerView) //TODO add temporary replace by transition animation
            notifyDataSetChanged()
        }
    }
}

data class ViewHolderRow(var isExtendedViewVisible: ObservableBoolean, val row: Row)

data class CreatingSetAdapterParams(
    val preinitializedRows: List<Row>,
    val allPartOfMasses: List<DropDownItem<Long, String>>,
    val allSongbookSongComplex: List<DropDownItem<Long, String>>? = null, //TODO nullables are temporary added
    val allSongbooks: List<DropDownItem<Long, String>>? = null
)