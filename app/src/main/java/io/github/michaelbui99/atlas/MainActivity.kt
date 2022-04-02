package io.github.michaelbui99.atlas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import io.github.michaelbui99.atlas.R

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);

        val navFragmentContainer =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        appBarConfig =
            AppBarConfiguration(
                setOf(
                    R.id.view_home,
                    R.id.view_search,
                    R.id.view_settings,
                    R.id.view_user,
                    R.id.view_login
                )
            )
        navController = navFragmentContainer!!.findNavController()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfig) || super.onNavigateUp()
    }

}