package com.example.mytranslator.ui.translatorfragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gb_pprog.domain.model.DomainModel
import com.example.mytranslator.R
import com.example.mytranslator.databinding.FragmentTranslatorBinding
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import com.example.mytranslator.imageloader.GlideImageLoader
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
        (requireActivity().application as TranslatorSubcomponentProvider).initTranslatorSubcomponent()
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
        initClickListener()
        textChangingHandler()
        val observer = Observer<TranslatorState> { renderData(it) }
        vm.translatorState.observe(viewLifecycleOwner, observer)
    }

    private fun initClickListener() {
        binding.translatorToFavoriteFab.setOnClickListener {
            findNavController().navigate(R.id.action_translatorFragment_to_favoriteFragment)
        }
    }

    private fun textChangingHandler() {
        binding.translatorTiet.doAfterTextChanged {
            vm.getTranslate(binding.translatorTiet.text.toString())
        }
    }

    private fun renderData(data: TranslatorState) {
        when (data) {
            is TranslatorState.Success -> {
                binding.translatorTil.helperText = ""
                refreshListAdapter(data.response)
            }
            is TranslatorState.Loading -> {
                binding.translatorTil.helperText = "LOADING"
            }
            is TranslatorState.Error -> {
                refreshListAdapter(emptyList())
                binding.translatorTil.error = data.error
            }
        }
    }

    private fun refreshListAdapter(list: List<DomainModel>?) = adapter.submitList(list)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}