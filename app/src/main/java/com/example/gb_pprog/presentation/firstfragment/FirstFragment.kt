package com.example.gb_pprog.presentation.firstfragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gb_pprog.databinding.FragmentFirstBinding
import com.example.gb_pprog.presentation.firstfragment.presenter.FirstPresenter
import com.example.gb_pprog.presentation.firstfragment.presenter.FirstView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FirstFragment : MvpAppCompatFragment(), FirstView {

    private val presenter by moxyPresenter {
        FirstPresenter()
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
        binding.ffTil.setEndIconOnClickListener {
            getTranslateData(binding.ffTiet.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTranslateData(inputWord: String) {

    }
}