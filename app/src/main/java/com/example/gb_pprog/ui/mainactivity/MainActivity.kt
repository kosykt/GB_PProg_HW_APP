package com.example.gb_pprog.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.gb_pprog.R
import com.example.gb_pprog.application.App
import com.example.gb_pprog.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TRANSLATOR_F = "translator"
private const val TIMER_F = "timer"
private const val FAVORITE_F = "favorite"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vmFactory: ViewModelProvider.Factory =
        App.instance.appComponent.injectViewModelFactory()
    private val vm: ActivityViewModel by lazy {
        ViewModelProvider(this, vmFactory)[ActivityViewModel::class.java]
    }
    private var fragmentOnView = TRANSLATOR_F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.getCountOfFavoriteWords()
        initBottomNavView()
        initBadge()
    }

    private fun initBadge() {
        binding.mainBnv.getOrCreateBadge(R.id.bottom_view_favorite_fragment)
        val badge = binding.mainBnv.getBadge(R.id.bottom_view_favorite_fragment)
        badge?.maxCharacterCount = 3
        lifecycleScope.launch {
            vm.countOfFavoriteWords.collect {
                badge?.number = it
            }
        }
    }

    private fun navigateToTranslatorFragment() {
        fragmentOnView = TRANSLATOR_F
        Navigation.findNavController(this, R.id.main_container)
            .popBackStack()
    }

    private fun navigateToTimerFragment() {
        fragmentOnView = TIMER_F
        Navigation.findNavController(this, R.id.main_container)
            .navigate(R.id.action_translatorFragment_to_timerFragment)
    }

    private fun navigateToFavoriteFragment() {
        fragmentOnView = FAVORITE_F
        Navigation.findNavController(this, R.id.main_container)
            .navigate(R.id.action_translatorFragment_to_favoriteFragment)
    }

    private fun initBottomNavView() {
        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_translator_fragment -> {
                    navigateToTranslatorFragment()
                    true
                }
                R.id.bottom_view_timer_fragment -> {
                    if (fragmentOnView != TRANSLATOR_F) {
                        navigateToTranslatorFragment()
                        navigateToTimerFragment()
                    } else {
                        navigateToTimerFragment()
                    }
                    true
                }
                R.id.bottom_view_favorite_fragment -> {
                    if (fragmentOnView != TRANSLATOR_F) {
                        navigateToTranslatorFragment()
                        navigateToFavoriteFragment()
                    } else {
                        navigateToFavoriteFragment()
                    }
                    true
                }
                else -> {
                    navigateToTranslatorFragment()
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
            else -> {
                binding.mainBnv.selectedItemId = R.id.bottom_view_translator_fragment
            }
        }
    }
}