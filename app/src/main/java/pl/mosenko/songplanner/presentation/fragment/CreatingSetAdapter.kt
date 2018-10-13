package pl.mosenko.songplanner.presentation.fragment

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import pl.mosenko.songplanner.data.model.PartOfMass
import pl.mosenko.songplanner.data.model.Row
import pl.mosenko.songplanner.databinding.CreatingRowItemBinding
import pl.mosenko.songplanner.presentation.adapter.DropDownArrayAdapter
import pl.mosenko.songplanner.presentation.adapter.DropDownItem

class CreatingSetAdapter(
        rowList: List<Row>,
        private val partOfMassList: List<PartOfMass>)
    : RecyclerView.Adapter<CreatingSetAdapter.ViewHolder>() {

    var viewHolderRowList: MutableList<ViewHolderRow> = rowList
            .map { ViewHolderRow(ObservableBoolean(false), it) }
            .toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(CreatingRowItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )).apply {
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

    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    inner class ViewHolder(private val creatingRowItemBinding: CreatingRowItemBinding)
        : RecyclerView.ViewHolder(creatingRowItemBinding.root) {

        fun bind(position: Int, viewHolderRow: ViewHolderRow) {
            creatingRowItemBinding.apply {
                row = viewHolderRow.row
                onExpandListener = View.OnClickListener { onExpandClickListener(position, viewHolderRow) }
                //convert list of part mass before this block in the outer class
                val partOfMassAdapter = DropDownArrayAdapter(itemView.context, R.layout.simple_dropdown_item_1line, partOfMassList.map { DropDownItem(it.partOfMassId!!, it.partOfMassName) })
                rowEditText.setDropDownArrayAdapter(partOfMassAdapter)
                isExtendedViewVisible = viewHolderRow.isExtendedViewVisible
            }
        }

        fun onExpandClickListener(position: Int, viewHolderRow: ViewHolderRow) {
            viewHolderRow.isExtendedViewVisible.set(!viewHolderRow.isExtendedViewVisible.get())
            TransitionManager.beginDelayedTransition(recyclerView)
//            notifyItemChanged(position)
            notifyDataSetChanged() // it's seems to work better
        }
    }
}

data class ViewHolderRow(var isExtendedViewVisible: ObservableBoolean, val row: Row)