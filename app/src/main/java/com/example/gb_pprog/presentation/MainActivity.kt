package com.example.gb_pprog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.ActivityMainBinding

private const val TRANSLATOR_F = "translator"
private const val TIMER_F = "timer"
private const val FAVORITE_F = "favorite"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fragmentOnView = TRANSLATOR_F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavView()
    }

    private fun initTranslatorFragment() {
        fragmentOnView = TRANSLATOR_F
        Navigation.findNavController(this, R.id.main_container)
            .popBackStack()
    }

    private fun initTimerFragment() {
        fragmentOnView = TIMER_F
        Navigation.findNavController(this, R.id.main_container)
            .navigate(R.id.action_translatorFragment_to_timerFragment)
    }

    private fun initFavoriteFragment() {
        fragmentOnView = FAVORITE_F
        Navigation.findNavController(this, R.id.main_container)
            .navigate(R.id.action_translatorFragment_to_favoriteFragment)
    }

    private fun initBottomNavView() {
        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_translator_fragment -> {
                    initTranslatorFragment()
                    true
                }
                R.id.bottom_view_timer_fragment -> {
                    if (fragmentOnView == FAVORITE_F) {
                        initTranslatorFragment()
                        initTimerFragment()
                    } else {
                        initTimerFragment()
                    }
                    true
                }
                R.id.bottom_view_favorite_fragment -> {
                    if (fragmentOnView == TIMER_F){
                        initTranslatorFragment()
                        initFavoriteFragment()
                    }else {
                        initFavoriteFragment()
                    }
                    true
                }
                else -> {
                    initTranslatorFragment()
                    true
                }
            }
        }
        binding.mainBnv.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.bottom_view_translator_fragment -> {}
                R.id.bottom_view_timer_fragment -> {}
                R.id.bottom_view_favorite_fragment -> {}
                else -> {}
            }
        }
    }

    override fun onBackPressed() {
        when (fragmentOnView) {
            TRANSLATOR_F -> {
                super.onBackPressed()
            }
            TIMER_F -> {
                binding.mainBnv.selectedItemId = R.id.bottom_view_translator_fragment
            }
            FAVORITE_F -> {
                binding.mainBnv.selectedItemId = R.id.bottom_view_translator_fragment
            }
        }
    }
}