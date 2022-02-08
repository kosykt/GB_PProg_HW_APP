package com.example.gb_pprog.mytimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gb_pprog.mytimer.databinding.FragmentTimerBinding
import com.example.gb_pprog.mytimer.utils.viewById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class TimerFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy {
        createScope(this)
    }

    private val vm: TimerViewModel by inject<TimerViewModel>()

    private var _binding: FragmentTimerBinding? = null
    private val binding: FragmentTimerBinding
        get() = _binding ?: throw RuntimeException("FragmentTimerBinding? = null")

    private val buttonStart by viewById<Button>(R.id.ft_start_btn)
    private val buttonPause by viewById<Button>(R.id.ft_pause_btn)
    private val buttonStop by viewById<Button>(R.id.ft_stop_btn)
    private val textView by viewById<TextView>(R.id.ft_timer_tv)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initTimerBinding()
    }

    private fun initTimerBinding() {
        lifecycleScope.launch(Dispatchers.Main) {
            vm.ticker.collect {
                textView.text = it
            }
        }
    }

    private fun initClickListeners() {
        buttonStart.setOnClickListener {
            vm.start()
        }
        buttonPause.setOnClickListener {
            vm.pause()
        }
        buttonStop.setOnClickListener {
            vm.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}