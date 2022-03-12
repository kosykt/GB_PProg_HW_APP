package com.example.gb_pprog.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.gb_pprog.R
import com.example.gb_pprog.application.App
import com.example.gb_pprog.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vmFactory: ViewModelProvider.Factory =
        App.instance.appComponent.injectViewModelFactory()
    private val vm: ActivityViewModel by lazy {
        ViewModelProvider(this, vmFactory)[ActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        initBadge()
    }

    private fun initBadge() {
        vm.getCountOfFavoriteWords()
        val badge = binding.mainBnv.getOrCreateBadge(R.id.favoriteFragment)
        badge.maxCharacterCount = 3
        lifecycleScope.launch {
            vm.countOfFavoriteWords.collect {
                badge.number = it
            }
        }
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavView = binding.mainBnv
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }
}