package com.example.mytranslator.ui.favoritefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytranslator.databinding.FragmentFavoriteBinding
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val vm: FavoriteViewModel by lazy {
        ViewModelProvider(this, vmFactory)[FavoriteViewModel::class.java]
    }
    private val adapter by lazy {
        FavoriteAdapter(
            onItemClickListener = vm::deleteFavorite
        )
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding? = null")

    override fun onAttach(context: Context) {
        (requireActivity().application as TranslatorSubcomponentProvider).initTranslatorSubcomponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteRv.adapter = adapter
        vm.favoriteWords.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        vm.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}