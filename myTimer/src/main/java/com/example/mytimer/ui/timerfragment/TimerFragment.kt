package com.example.mytimer.ui.timerfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.mytimer.databinding.FragmentTimerBinding
import com.example.mytimer.di.TimerSubcomponentProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimerFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val vm: TimerViewModel by lazy {
        ViewModelProvider(this, vmFactory)[TimerViewModel::class.java]
    }

    private var _binding: FragmentTimerBinding? = null
    private val binding: FragmentTimerBinding
        get() = _binding ?: throw RuntimeException("FragmentTimerBinding? = null")

    override fun onAttach(context: Context) {
        (requireActivity().application as TimerSubcomponentProvider).initTimerSubcomponent()
            .inject(this)
        super.onAttach(context)
    }

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
            vm.ticker
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
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