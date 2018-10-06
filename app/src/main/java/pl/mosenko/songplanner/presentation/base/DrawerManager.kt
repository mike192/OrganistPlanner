package pl.mosenko.songplanner.presentation.base

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout

interface DrawerManager {
    fun onBackPressed()
    fun addDrawerListener(): ActionBarDrawerToggle
    fun removeDrawerListener(drawerListener: DrawerLayout.DrawerListener)
}