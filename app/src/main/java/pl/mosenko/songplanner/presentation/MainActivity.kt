package pl.mosenko.songplanner.presentation

import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import pl.mosenko.songplanner.R
import pl.mosenko.songplanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: androidx.drawerlayout.widget.DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navigationController = Navigation.findNavController(this, R.id.main_nav_fragment)
        setSupportActionBar(binding.toolbar)
//        NavigationUI.setupActionBarWithNavController(this, navigationController, drawerLayout)

//        binding.navigationView.setupWithNavController()

        setContentView(R.layout.activity_main)
    }
}
