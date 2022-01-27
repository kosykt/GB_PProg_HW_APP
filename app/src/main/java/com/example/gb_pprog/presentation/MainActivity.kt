package com.example.gb_pprog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavView()
    }

    private fun initFirstFragment() {
        Navigation.findNavController(this, R.id.main_container)
            .popBackStack()
    }

    private fun initTimerFragment() {
        Navigation.findNavController(this, R.id.main_container)
            .navigate(R.id.action_firstFragment_to_timerFragment)
    }

    private fun initBottomNavView() {
        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_ff -> {
                    initFirstFragment()
                    true
                }
                R.id.bottom_view_timer_fragment -> {
                    initTimerFragment()
                    true
                }
                else -> {
                    initFirstFragment()
                    true
                }
            }
        }
        binding.mainBnv.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.bottom_view_ff -> {}
                R.id.bottom_view_timer_fragment -> {}
                else -> {}
            }
        }
    }
}