package pl.mosenko.songplanner.presentation.fragment

import android.content.Context
import androidx.appcompat.app.ActionBarDrawerToggle

open class HomeBaseFragment : BaseFragment() {
    private var drawerListener: ActionBarDrawerToggle? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        drawerListener = mainActivity?.addDrawerListener()
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity?.removeDrawerListener(drawerListener!!)
    }
}