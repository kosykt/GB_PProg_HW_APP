package com.example.gb_pprog.presentation.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.data.network.ApiHolder
import com.example.gb_pprog.data.network.DataSourceNetwork
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.databinding.FragmentFirstBinding
import com.example.gb_pprog.domain.SearchWordUseCase
import com.example.gb_pprog.presentation.firstfragment.adapter.FirstAdapter
import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModel
import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModelFactory

class FirstFragment : Fragment() {

    private val retrofitService = ApiHolder.retrofitService
    private val dataSourceRepository = DataSourceNetwork(retrofitService)
    private val domainRepository = DomainRepositoryImpl(dataSourceRepository)
    private val searchWordUseCase = SearchWordUseCase(domainRepository)

    private val viewModel: FirstViewModel by lazy {
        ViewModelProvider(
            this,
            FirstViewModelFactory(searchWordUseCase)
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
        viewModel.responseData.observe(viewLifecycleOwner) {
            initObserver()
        }
        binding.ffRv.adapter = adapter
        initTextInputLayout()
    }

    private fun initObserver() {
        adapter.submitList(viewModel.responseData.value)
    }

    private fun initTextInputLayout() {
        binding.ffTil.apply {
            editText?.doOnTextChanged { _, _, _, _ ->
                if (!binding.ffTiet.text.isNullOrEmpty()) {
                    viewModel.getTranslate(binding.ffTiet.text.toString())
                    initObserver()
                } else {
                    adapter.submitList(null)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}