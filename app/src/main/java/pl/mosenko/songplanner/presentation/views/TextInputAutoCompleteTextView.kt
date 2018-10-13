package pl.mosenko.songplanner.presentation.views

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import pl.mosenko.songplanner.R

@TargetApi(Build.VERSION_CODES.N)
open class TextInputAutoCompleteTextView(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
        popupTheme: Resources.Theme?)
    : AutoCompleteTextView(
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

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
        val ic = super.onCreateInputConnection(outAttrs)
        if (ic != null && outAttrs.hintText == null) {
            outAttrs.hintText = this.getHintFromLayout()
        }

        return ic
    }

    private fun getTextInputLayout(): TextInputLayout? {
        var parent = this.parent
        while (parent is View) {
            if (parent is TextInputLayout) {
                return parent
            }
            parent = parent.getParent()
        }

        return null
    }

    private fun getHintFromLayout(): CharSequence? {
        val layout = this.getTextInputLayout()
        return layout?.hint
    }
}