package pl.mosenko.songplanner.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: androidx.drawerlayout.widget.DrawerLayout
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        navigationController = Navigation.findNavController(this, R.id.main_nav_fragment)
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navigationController, drawerLayout)
        binding.navigationView.setupWithNavController(navigationController)
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
