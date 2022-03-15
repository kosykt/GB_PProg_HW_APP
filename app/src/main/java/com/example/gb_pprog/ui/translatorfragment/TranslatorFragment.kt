package com.example.gb_pprog.ui.translatorfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gb_pprog.R
import com.example.gb_pprog.application.App
import com.example.gb_pprog.databinding.FragmentTranslatorBinding
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.imageloader.GlideImageLoader
import com.example.gb_pprog.ui.translatorfragment.adapter.TranslatorAdapter
import com.example.gb_pprog.ui.translatorfragment.viewmodel.TranslatorViewModel
import kotlinx.coroutines.launch

class TranslatorFragment : Fragment() {

    private val vmFactory: ViewModelProvider.Factory =
        App.instance.appComponent.injectViewModelFactory()
    private val vm: TranslatorViewModel by lazy {
        ViewModelProvider(this, vmFactory)[TranslatorViewModel::class.java]
    }

    private val adapter by lazy {
        TranslatorAdapter(
            imageLoader = GlideImageLoader(),
            onItemClickListener = vm::favoriteWordOperator,
            checkIsFavorite = vm::checkIsFavorite
        )
    }

    private var _binding: FragmentTranslatorBinding? = null
    private val binding: FragmentTranslatorBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding? = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.translatorRv.adapter = adapter
        initObservers()
        initTextInputLayout()
        initClickListener()
    }

    private fun initClickListener() {
        binding.translatorToFavoriteFab.setOnClickListener {
            findNavController().navigate(R.id.action_translatorFragment_to_favoriteFragment)
        }
    }

    private fun initObservers() {
        vm.responseData.observe(viewLifecycleOwner) {
            refreshListAdapter(it)
        }
        vm.errorText.observe(viewLifecycleOwner) {
            setErrorText(it)
        }
    }

    private fun refreshListAdapter(list: List<DomainModel>?) = adapter.submitList(list)

    private fun initTextInputLayout() {
        binding.translatorTil.apply {
            editText?.doAfterTextChanged {
                lifecycleScope.launch {
                    vm.getTranslate(binding.translatorTiet.text.toString())
                }
            }
        }
    }

    private fun setErrorText(errorText: String?) {
        binding.translatorTil.error = errorText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}