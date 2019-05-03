package pl.mosenko.songplanner.core.views.bindings

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import pl.mosenko.songplanner.core.extensions.addAfterTextChangedListener

private const val NOT_EXISTED_INDEX = -1

@BindingAdapter("selectedValue")
@Suppress("UNCHECKED_CAST")
fun AutoCompleteTextView.selectedValue(selectedValue: MutableLiveData<String>) {
    if (adapter != null && getSelectedValue() != selectedValue.value) {
        val arrayAdapter = adapter as? ArrayAdapter<String?>
            ?: throw IllegalStateException("Adapter have to inherit from ArrayAdapter")
        val valuePosition = arrayAdapter.getPosition(selectedValue.value)
        if (valuePosition != NOT_EXISTED_INDEX) {
            setSelection(valuePosition)
        } else {
            this@selectedValue.setText(selectedValue.value)
        }
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun AutoCompleteTextView.getSelectedValue(): String? = text.toString()

@BindingAdapter("selectedValueAttrChanged")
fun AutoCompleteTextView.setInverseBindingAdapter(listener: InverseBindingListener) =
    addAfterTextChangedListener { listener.onChange() }
