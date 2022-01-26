package com.example.gb_pprog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.ActivityMainBinding
import com.example.gb_pprog.hw4.presentation.TimerFragment
import com.example.gb_pprog.presentation.firstfragment.FirstFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirstFragment()
        initBottomNavView()
    }

    private fun initFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FirstFragment.newInstance())
            .commit()
    }

    private fun initTimerFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, TimerFragment.newInstance())
            .commit()
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