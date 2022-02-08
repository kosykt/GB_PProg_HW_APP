package com.example.gb_pprog.presentation.mainactivity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope

private const val TRANSLATOR_F = "translator"
private const val TIMER_F = "timer"
private const val FAVORITE_F = "favorite"

class MainActivity : AppCompatActivity(), KoinScopeComponent {

    override val scope: Scope by lazy {
        createScope(this)
    }

    private lateinit var binding: ActivityMainBinding
    private val vm: ActivityViewModel by inject()
    private var fragmentOnView = TRANSLATOR_F

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.S) {
            installSplashScreen()
        }
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

    private fun initBottomNavView() {
        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_translator_fragment -> {
                    navigateToTranslatorFragment()
                    true
                }
                R.id.bottom_view_timer_fragment -> {
                    if (fragmentOnView == FAVORITE_F) {
                        navigateToTranslatorFragment()
                        navigateToTimerFragment()
                    } else {
                        navigateToTimerFragment()
                    }
                    true
                }
                R.id.bottom_view_favorite_fragment -> {
                    if (fragmentOnView == TIMER_F) {
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
            TIMER_F -> {
                binding.mainBnv.selectedItemId = R.id.bottom_view_translator_fragment
            }
            FAVORITE_F -> {
                binding.mainBnv.selectedItemId = R.id.bottom_view_translator_fragment
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}