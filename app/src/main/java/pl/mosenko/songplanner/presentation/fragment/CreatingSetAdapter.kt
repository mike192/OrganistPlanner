package pl.mosenko.songplanner.presentation.fragment

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import pl.mosenko.songplanner.data.model.PartOfMass
import pl.mosenko.songplanner.data.model.Row
import pl.mosenko.songplanner.databinding.CreatingRowItemBinding
import pl.mosenko.songplanner.presentation.adapter.DropDownArrayAdapter
import pl.mosenko.songplanner.presentation.adapter.DropDownItem

class CreatingSetAdapter(
        private val rowList: List<Row>,
        private val partOfMassList: List<PartOfMass>)
    : RecyclerView.Adapter<CreatingSetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CreatingRowItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun getItemCount(): Int {
        return rowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rowList.get(position))
    }

    inner class ViewHolder(private val creatingRowItemBinding: CreatingRowItemBinding)
        : RecyclerView.ViewHolder(creatingRowItemBinding.root) {

        fun bind(row: Row) {
            creatingRowItemBinding.apply {
                this.row = row
                this.onExpandListener = View.OnClickListener { onExpandClickListener(it) }
                //convert list of part mass before this block in the outer class
                val partOfMassAdapter = DropDownArrayAdapter(itemView.context, R.layout.simple_dropdown_item_1line, partOfMassList.map { DropDownItem(it.partOfMassId!!, it.partOfMassName) })
                this.rowEditText.setDropDownArrayAdapter(partOfMassAdapter)
            }
        }

        fun onExpandClickListener(view: View?) {
            Toast.makeText(view!!.context, "Kliknieto!!!", Toast.LENGTH_LONG).show()
        }
    }
}