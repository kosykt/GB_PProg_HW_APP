package com.example.mytranslator.ui.translatorfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gb_pprog.domain.model.DomainModel
import com.example.mytranslator.R
import com.example.mytranslator.databinding.FragmentTranslatorBinding
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import com.example.mytranslator.ui.detailsfragment.DetailsModel
import kotlinx.coroutines.flow.collect
import ru.kosykt.utils.imageloader.ImageLoader
import javax.inject.Inject

class TranslatorFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: ImageLoader

    private val vm: TranslatorViewModel by lazy {
        ViewModelProvider(this, vmFactory)[TranslatorViewModel::class.java]
    }

    private val adapter by lazy {
        TranslatorAdapter(
            imageLoader = imageLoader,
            onItemClickListener = vm::favoriteWordOperator,
            checkIsFavorite = vm::checkIsFavorite,
            string = requireContext().getString(R.string.translator_item_tv_translate_text),
            navigateClickListener = this::navigateToDetails
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.translatorRv.adapter = adapter
        initClickListener()
        textChangingHandler()
        lifecycleScope.launchWhenStarted {
            vm.translatorState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    renderData(it)
                }
        }
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

    private fun navigateToDetails(model: DomainModel) {
        val detailsModel = DetailsModel(
            word = model.text,
            transcription = model.meanings[0].transcription,
            translation = model.meanings[0].translation.text,
            note = model.meanings[0].translation.note,
            imageUrl = model.meanings[0].imageUrl
        )
        findNavController().navigate(
            TranslatorFragmentDirections.actionTranslatorFragmentToDetailsFragment(detailsModel)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}