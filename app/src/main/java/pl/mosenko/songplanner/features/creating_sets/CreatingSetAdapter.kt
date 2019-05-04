package pl.mosenko.songplanner.features.creating_sets

import android.content.Context
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
    context: Context,
    creatingSetAdapterParams: CreatingSetAdapterParams
) : RecyclerView.Adapter<CreatingSetAdapter.ViewHolder>() {

    private lateinit var recyclerView: RecyclerView
    private val partOfMassAdapter = DropDownArrayAdapter(
        context,
        android.R.layout.simple_dropdown_item_1line,
        creatingSetAdapterParams.allPartOfMasses
    )

    private val songAdapter = DropDownArrayAdapter(
        context,
        android.R.layout.simple_dropdown_item_1line,
        creatingSetAdapterParams.allSongs
    )

    private val songbookAdapter = DropDownArrayAdapter(
        context,
        android.R.layout.simple_dropdown_item_1line,
        creatingSetAdapterParams.allSongbooks
    )

    private var viewHolderRowList: MutableList<ViewHolderRow> =
        creatingSetAdapterParams.preInitializedRows
            .map { ViewHolderRow(ObservableBoolean(false), it) }
            .toMutableList()

    fun getRowList(): List<Row> = viewHolderRowList.map { it.row }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CreatingRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)
     //   viewHolder.setIsRecyclable(false)
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
                    View.OnClickListener {
                        onExpandClickListener(viewHolderRow)
                        notifyItemChanged(position)
                        creatingRowItemBinding.executePendingBindings()
                    }
                partOfMassInput.setDropDownArrayAdapter(partOfMassAdapter)
                basicSongInput.setDropDownArrayAdapter(songAdapter)
                basicSongInput.addAfterTextChangedListener { text ->
                    if (viewHolderRow.row.songbookSong!!.song?.songName != text) {
                        viewHolderRow.row.songbookSong!!.song = Song(text)
                        executePendingBindings()
                    }
                }
                songInput.setDropDownArrayAdapter(songAdapter)
                songInput.onItemClickListener =
                        //TODO load lists for songbook, etc
                    AdapterView.OnItemClickListener { _, _, _, _ ->
                        viewHolderRow.row.songbookSong!!.song =
                            songInput.getCurrentlySelectedObject().originalObject as Song
                    }
                songInput.addAfterTextChangedListener { text ->
                    if (viewHolderRow.row.songbookSong!!.song?.songName != text) {
                        viewHolderRow.row.songbookSong!!.song = Song(text)
                    }
                }
                songbookInput.setDropDownArrayAdapter(songbookAdapter)
                songbookInput.addAfterTextChangedListener { text ->
                    viewHolderRow.row.songbookSong!!.songbook = Songbook(text)
                }
                versesNumbersInput.addAfterTextChangedListener { text ->
                    viewHolderRow.row.versesNumbers = text
                }
                numberInSongbookInput.addAfterTextChangedListener { text ->
                    viewHolderRow.row.songbookSong!!.numberInSongbook = text
                }
                isExtendedViewVisible = viewHolderRow.isExtendedViewVisible
            }
        }

        private fun onExpandClickListener(viewHolderRow: ViewHolderRow) {
            viewHolderRow.isExtendedViewVisible.set(!viewHolderRow.isExtendedViewVisible.get())
           // TransitionManager.beginDelayedTransition(recyclerView)
        }
    }
}

data class ViewHolderRow(var isExtendedViewVisible: ObservableBoolean, val row: Row)

class CreatingSetAdapterParams(
    var preInitializedRows: List<Row> = emptyList(),
    var allPartOfMasses: List<DropDownItem<Long, String>> = emptyList(),
    var allSongs: List<DropDownItem<Long, String>> = emptyList(),
    var allSongbooks: List<DropDownItem<Long, String>> = emptyList()
)