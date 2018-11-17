package pl.mosenko.songplanner.presentation.adapter

import android.content.Context
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes

class DropDownArrayAdapter<KEY, VALUE>(
    context: Context, @LayoutRes resource: Int,
    private val list: List<DropDownItem<KEY, VALUE>>
) : ArrayAdapter<DropDownItem<KEY, VALUE>>(context, resource, 0, list) {

    fun findItemByString(value: String): DropDownItem<KEY, VALUE>? {
        return list.firstOrNull {
            it.value == value
        }
    }
}