package com.example.gb_pprog.presentation.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.FragmentFirstBinding
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.presentation.firstfragment.adapter.FirstAdapter
import com.example.gb_pprog.presentation.firstfragment.presenter.FirstPresenter
import com.example.gb_pprog.presentation.firstfragment.presenter.FirstView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FirstFragment : MvpAppCompatFragment(), FirstView {

    private val presenter by moxyPresenter {
        FirstPresenter()
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
        binding.ffTil.setEndIconOnClickListener {
            if (binding.ffTiet.text.isNullOrEmpty()) {
                binding.ffTil.hint = getString(R.string.ff_til_hint_text_is_null_or_empty)
            } else {
                binding.ffTil.hint = ""
                presenter.translate(binding.ffTiet.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTranslateData(data: List<DomainModel>) {
        adapter.submitList(data)
    }

}