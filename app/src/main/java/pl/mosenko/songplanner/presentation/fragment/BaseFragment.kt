package pl.mosenko.songplanner.presentation.fragment

import android.content.Context
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import pl.mosenko.songplanner.presentation.MainActivity
import java.lang.ref.WeakReference

open class BaseFragment : Fragment() {
    private var _mainActivity: WeakReference<MainActivity?>? = null
    var mainActivity: MainActivity?
        get() = _mainActivity?.get()
        set(value) {
            _mainActivity = WeakReference(value)
        }

    @CallSuper
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }
}