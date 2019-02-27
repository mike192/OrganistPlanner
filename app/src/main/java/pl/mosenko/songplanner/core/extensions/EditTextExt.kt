package pl.mosenko.songplanner.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addAfterTextChangedListener(listener: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(text: Editable?) {
            listener(text.toString())
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            //no-op
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            //no-op
        }
    })
}