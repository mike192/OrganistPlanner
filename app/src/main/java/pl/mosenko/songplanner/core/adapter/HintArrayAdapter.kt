package pl.mosenko.songplanner.core.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

class HintArrayAdapter<T>(
    context: Context,
    @LayoutRes resource: Int = android.R.layout.simple_dropdown_item_1line,
    @IdRes textViewResourceId: Int = 0,
    objects: List<T> = ArrayList()
) : ArrayAdapter<T>(context, resource, textViewResourceId, objects) {

    override fun isEnabled(position: Int) = position != FIRST_POSITION_INDEX

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dropDownView = super.getDropDownView(position, convertView, parent)
                as? TextView
            ?: throw IllegalStateException("DropDownView has to have TextView type")
        if (position == FIRST_POSITION_INDEX) {
            dropDownView.setTextColor(Color.GRAY)
        } else {
            dropDownView.setTextColor(Color.BLACK)
        }
        return dropDownView
    }

    companion object {
        const val FIRST_POSITION_INDEX = 0
    }
}