package com.example.gb_pprog.presentation.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.App
import com.example.gb_pprog.databinding.FragmentFirstBinding
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.presentation.firstfragment.adapter.FirstAdapter
import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModel

class FirstFragment : Fragment() {

    private val viewModel: FirstViewModel by lazy {
        ViewModelProvider(
            this,
            App.appComponent.injectFirstViewModelFactory()
        )[FirstViewModel::class.java]
    }

    private val adapter by lazy {
        FirstAdapter()
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding? = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ffRv.adapter = adapter
        initObservers()
        initTextInputLayout()
    }

    private fun initObservers() {
        viewModel.responseData.observe(viewLifecycleOwner) {
            refreshListAdapter(it)
        }
        viewModel.loadingData.observe(viewLifecycleOwner) {
            refreshLoadingView(it)
        }
        viewModel.errorText.observe(viewLifecycleOwner) {
            setErrorText(it)
        }
    }

    private fun refreshListAdapter(list: List<DomainModel>?) = adapter.submitList(list)

    private fun initTextInputLayout() {
        binding.ffTil.apply {
            editText?.doOnTextChanged { _, _, _, _ ->
                viewModel.getTranslate(binding.ffTiet.text.toString())
                refreshListAdapter(viewModel.responseData.value)
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