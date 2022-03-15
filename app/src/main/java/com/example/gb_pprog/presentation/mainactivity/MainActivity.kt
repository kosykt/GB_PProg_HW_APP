package com.example.gb_pprog.presentation.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TRANSLATOR_F = "translator"
private const val TIMER_F = "timer"
private const val FAVORITE_F = "favorite"
private const val EMAIL_VALIDATOR_F = "validator"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: ActivityViewModel by viewModel<ActivityViewModel>()
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
        lifecycleScope.launch {
            vm.countOfFavoriteWords.collect {
                val badge = binding.mainBnv.getBadge(R.id.bottom_view_favorite_fragment)
                badge?.maxCharacterCount = 3
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

    private fun navigateToEmailValidatorFragment() {
        fragmentOnView = EMAIL_VALIDATOR_F
        Navigation.findNavController(this, R.id.main_container)
            .navigate(R.id.action_translatorFragment_to_myFirstTestFragment)
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
                R.id.bottom_view_email_validator_fragment -> {
                    if (fragmentOnView != TRANSLATOR_F){
                        navigateToTranslatorFragment()
                        navigateToEmailValidatorFragment()
                    } else {
                        navigateToEmailValidatorFragment()
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