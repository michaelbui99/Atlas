package io.github.michaelbui99.atlas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration;
    private lateinit var navController: NavController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);

        val navFragmentContainer = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val navView : NavigationView = findViewById(R.id.nav_view)

        // TODO: Setup drawer layout and include in appBarConfig
        // TODO: Add all top level fragments
        appBarConfig = AppBarConfiguration(setOf(R.id.view_home))
        navController = navFragmentContainer!!.findNavController()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)
        navView.setupWithNavController(navController)
    }
}