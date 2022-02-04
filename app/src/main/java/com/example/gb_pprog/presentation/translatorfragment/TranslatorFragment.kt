package com.example.gb_pprog.presentation.translatorfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gb_pprog.databinding.FragmentTranslatorBinding
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.presentation.imageloader.GlideImageLoader
import com.example.gb_pprog.presentation.translatorfragment.adapter.TranslatorAdapter
import com.example.gb_pprog.presentation.translatorfragment.viewmodel.TranslatorViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class TranslatorFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy {
        createScope(this)
    }

    private val vm by inject<TranslatorViewModel>()
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

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}