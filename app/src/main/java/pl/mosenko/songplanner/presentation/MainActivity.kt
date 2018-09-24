package pl.mosenko.songplanner.presentation

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationController: NavController
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(activityMainBinding.toolbar)
        drawerLayout = activityMainBinding.drawerLayout
        navigationController = Navigation.findNavController(this, R.id.main_nav_fragment)
//        NavigationUI.setupActionBarWithNavController(this, navigationController, drawerLayout)
        activityMainBinding.navigationView.setupWithNavController(navigationController)
    }

    fun addDrawerListener(): ActionBarDrawerToggle {
        val toggle = ActionBarDrawerToggle(this, drawerLayout, activityMainBinding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        return toggle
    }

    fun removeDrawerListener(drawerListener: DrawerLayout.DrawerListener) {
        drawerLayout.removeDrawerListener(drawerListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawerLayout, navigationController)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
