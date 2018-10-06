package pl.mosenko.songplanner.presentation.fragment

import androidx.annotation.CallSuper
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import pl.mosenko.songplanner.presentation.base.DrawerManager
import java.lang.ref.WeakReference

open class BaseFragment : Fragment() {
    private var _drawerManager: WeakReference<DrawerManager?>? = null
    var drawerManager: DrawerManager?
        get() = _drawerManager?.get()
        set(value) {
            _drawerManager = WeakReference(value)
        }

    var drawerListener: ActionBarDrawerToggle? = null

    @CallSuper
    override fun onStart() {
        super.onStart()
        this.drawerManager = activity as DrawerManager
        drawerListener = this.drawerManager?.addDrawerListener()
        enableDrawer(true)
    }

    override fun onStop() {
        super.onStop()
        this.drawerManager?.removeDrawerListener(drawerListener!!)
        this.drawerManager = null
    }

    fun enableDrawer(enable: Boolean) {
        drawerListener?.isDrawerIndicatorEnabled = enable
        if (!enable) {
            drawerListener?.setToolbarNavigationClickListener { drawerManager?.onBackPressed() }
        }
    }
}