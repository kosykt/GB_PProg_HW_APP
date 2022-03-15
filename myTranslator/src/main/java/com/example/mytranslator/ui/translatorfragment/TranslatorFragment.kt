package com.example.mytranslator.ui.translatorfragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gb_pprog.domain.model.DomainModel
import com.example.mytranslator.R
import com.example.mytranslator.databinding.FragmentTranslatorBinding
import com.example.mytranslator.di.TranslatorProvider
import com.example.mytranslator.imageloader.GlideImageLoader
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("UseCompatLoadingForDrawables")
class TranslatorFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val vm: TranslatorViewModel by lazy {
        ViewModelProvider(this, vmFactory)[TranslatorViewModel::class.java]
    }

    private val adapter by lazy {
        TranslatorAdapter(
            imageLoader = GlideImageLoader(),
            onItemClickListener = vm::favoriteWordOperator,
            checkIsFavorite = vm::checkIsFavorite,
            activeDrawable = requireContext().getDrawable(R.drawable.ic_baseline_favorite_active),
            diActiveDrawable = requireContext().getDrawable(R.drawable.ic_baseline_favorite_diactive),
            string = requireContext().getString(R.string.translator_item_tv_translate_text)
        )
    }

    private var _binding: FragmentTranslatorBinding? = null
    private val binding: FragmentTranslatorBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding? = null")

    override fun onAttach(context: Context) {
        (requireActivity().application as TranslatorProvider).initTranslatorSubcomponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
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