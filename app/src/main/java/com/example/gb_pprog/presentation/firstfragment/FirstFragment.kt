package com.example.gb_pprog.presentation.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.FragmentFirstBinding
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.hw4.presentation.TimerFragment
import com.example.gb_pprog.presentation.firstfragment.adapter.FirstAdapter
import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment() {

    private val vm by viewModel<FirstViewModel>()

    private val adapter by lazy {
        FirstAdapter()
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding? = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ffRv.adapter = adapter
        initObservers()
        initTextInputLayout()
        initTimerFragment()
    }

    private fun initTimerFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, TimerFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun initObservers() {
        vm.responseData.observe(viewLifecycleOwner) {
            refreshListAdapter(it)
        }
        vm.loadingData.observe(viewLifecycleOwner) {
            refreshLoadingView(it)
        }
        vm.errorText.observe(viewLifecycleOwner) {
            setErrorText(it)
        }
    }

    private fun refreshListAdapter(list: List<DomainModel>?) = adapter.submitList(list)

    private fun initTextInputLayout() {
        binding.ffTil.apply {
            editText?.doAfterTextChanged {
                lifecycleScope.launch {
                    vm.getTranslate(binding.ffTiet.text.toString())
                }
            }
        }
    }

    private fun setErrorText(errorText: String?) {
        binding.ffTil.error = errorText
    }

    private fun refreshLoadingView(value: Boolean?) {
        when (value) {
            true -> {
                binding.ffLoadingIv.visibility = View.VISIBLE
            }
            else -> {
                binding.ffLoadingIv.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}