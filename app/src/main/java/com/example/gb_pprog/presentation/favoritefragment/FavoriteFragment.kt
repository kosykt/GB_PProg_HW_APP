package com.example.gb_pprog.presentation.favoritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gb_pprog.databinding.FragmentFavoriteBinding
import com.example.gb_pprog.presentation.favoritefragment.adapter.FavoriteAdapter
import com.example.gb_pprog.presentation.favoritefragment.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val vm by viewModel<FavoriteViewModel>()
    private val adapter by lazy {
        FavoriteAdapter()
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding? = null")

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
        vm.favoriteWords.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        vm.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}