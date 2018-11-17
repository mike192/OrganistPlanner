package pl.mosenko.songplanner.features.about_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.mosenko.songplanner.core.platform.BaseFragment
import pl.mosenko.songplanner.databinding.FragmentAboutAppBinding

class AboutAppFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentAboutAppBinding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return fragmentAboutAppBinding.root
    }
}