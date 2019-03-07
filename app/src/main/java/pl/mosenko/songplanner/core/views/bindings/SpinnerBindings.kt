package pl.mosenko.songplanner.core.views.bindings

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData

@BindingAdapter("selectedValue")
@Suppress("UNCHECKED_CAST")
fun Spinner.selectedValue(selectedValue: MutableLiveData<String?>) {
    adapter?.run {
        val arrayAdapter = this as? ArrayAdapter<String?>
            ?: throw IllegalStateException("Adapter have to inherit from ArrayAdapter")
        val position = arrayAdapter.getPosition(selectedValue.value)
        if (selectedItemPosition != position) {
            setSelection(position)
            tag = position
        }
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): String? = selectedItem as String?

@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setInverseBindingListener(listener: InverseBindingListener) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            if (tag != position) {
                listener.onChange()
            }
        }
    }
}
