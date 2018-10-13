package pl.mosenko.songplanner.presentation.views

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.AttributeSet
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.presentation.adapter.DropDownArrayAdapter
import pl.mosenko.songplanner.presentation.adapter.DropDownItem

@TargetApi(Build.VERSION_CODES.N)
class ObjectTextInputAutoCompleteTextView(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
        popupTheme: Resources.Theme?)
    : TextInputAutoCompleteTextView(
        context, attrs,
        defStyleAttr,
        defStyleRes,
        popupTheme) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, R.attr.autoCompleteTextViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : this(context, attrs, defStyleAttr, defStyleRes, null)

    private lateinit var adapter: DropDownArrayAdapter<Long, String>

    fun setDropDownArrayAdapter(adapter: DropDownArrayAdapter<Long, String>) {
        this.adapter = adapter
        super.setAdapter(adapter)
    }

    fun getCurrentlySelectedObject(): DropDownItem<Long, String> {
        val input = text.toString()
        val selectedItem = adapter.findItemByString(input)
        return selectedItem ?: DropDownItem(NEW_DROP_DOWN_ITEM_KEY, input)
    }

    companion object {
        const val NEW_DROP_DOWN_ITEM_KEY = -1L
    }
}