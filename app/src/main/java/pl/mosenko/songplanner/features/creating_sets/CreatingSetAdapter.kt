package pl.mosenko.songplanner.features.creating_sets

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import pl.mosenko.songplanner.core.adapter.DropDownArrayAdapter
import pl.mosenko.songplanner.core.adapter.DropDownItem
import pl.mosenko.songplanner.core.extensions.addAfterTextChangedListener
import pl.mosenko.songplanner.data.row.Row
import pl.mosenko.songplanner.data.song.Song
import pl.mosenko.songplanner.data.songbook.Songbook
import pl.mosenko.songplanner.databinding.CreatingRowItemBinding

class CreatingSetAdapter(
    private val creatingSetAdapterParams: CreatingSetAdapterParams
) : RecyclerView.Adapter<CreatingSetAdapter.ViewHolder>() {

    private lateinit var recyclerView: RecyclerView
    private val partOfMassAdapter: DropDownArrayAdapter<Long, String>
        get() = DropDownArrayAdapter(
            recyclerView.context,
            R.layout.simple_dropdown_item_1line,
            creatingSetAdapterParams.allPartOfMasses
        )

    private val songAdapter: DropDownArrayAdapter<Long, String>
        get() = DropDownArrayAdapter(
            recyclerView.context,
            R.layout.simple_dropdown_item_1line,
            creatingSetAdapterParams.allSongs
        )

    private val songbookAdapter: DropDownArrayAdapter<Long, String>
        get() = DropDownArrayAdapter(
            recyclerView.context,
            R.layout.simple_dropdown_item_1line,
            creatingSetAdapterParams.allSongbooks
        )

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
                songInput.setDropDownArrayAdapter(songAdapter)
                songInput.onItemClickListener =
                        //TODO load lists for songbook, etc
                    AdapterView.OnItemClickListener { _, _, _, _ ->
                        viewHolderRow.row.songbookSong!!.song =
                            songInput.getCurrentlySelectedObject().originalObject as Song
                    }
                songInput.addAfterTextChangedListener { text ->
                    viewHolderRow.row.songbookSong!!.song = Song(text)
                }
                songbookInput.setDropDownArrayAdapter(songbookAdapter)
                songbookInput.addAfterTextChangedListener {
                    text ->
                    viewHolderRow.row.songbookSong!!.songbook = Songbook(text)
                }
                versesNumbersInput.addAfterTextChangedListener {text ->
                    viewHolderRow.row.versesNumbers = text
                }
                numberInSongbookInput.addAfterTextChangedListener {text ->
                    viewHolderRow.row.songbookSong!!.numberInSongbook = text
                }
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
    val allSongs: List<DropDownItem<Long, String>>,
    val allSongbooks: List<DropDownItem<Long, String>>
)