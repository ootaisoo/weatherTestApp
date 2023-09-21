package com.example.gismeteoapitestapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.databinding.ActivityMainBinding
import com.example.gismeteoapitestapp.ui.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    private val mainViewModel: MainViewModel by viewModels() {
        component.viewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appbarMain.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appbarMain.toolbar,
            R.string.open, R.string.close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.history -> mainViewModel.showRequestsHistory()
                R.id.home -> mainViewModel.showHome()
            }
            binding.drawerLayout.closeDrawers()
            true
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mainViewModel.onAttach(this)
        mainViewModel.showHome()
    }
}