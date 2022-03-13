package com.example.gb_pprog.ui.timerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.gb_pprog.databinding.FragmentTimerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TimerFragment : Fragment() {

    private val vmFactory: ViewModelProvider.Factory = TODO()
    private val vm: TimerViewModel by lazy {
        ViewModelProvider(this, vmFactory)[TimerViewModel::class.java]
    }

    private var _binding: FragmentTimerBinding? = null
    private val binding: FragmentTimerBinding
        get() = _binding ?: throw RuntimeException("FragmentTimerBinding? = null")

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
                binding.ftTimerTv.text = it
            }
        }
    }

    private fun initClickListeners() {
        binding.apply {
            ftStartBtn.setOnClickListener {
                vm.start()
            }
            ftPauseBtn.setOnClickListener {
                vm.pause()
            }
            ftStopBtn.setOnClickListener {
                vm.stop()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}