package com.example.gb_pprog.presentation.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
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
        initTextInputLayout()
    }

    private fun initTextInputLayout() {
        binding.ffTil.apply {
            editText?.doOnTextChanged { _, _, _, _ ->
                binding.ffTil.error = null
            }
            setEndIconOnClickListener {
                if (binding.ffTiet.text.isNullOrEmpty()) {
                    error = getString(R.string.ff_til_error_tiet_is_empty)
                } else {
                    presenter.translate(binding.ffTiet.text.toString())
                    binding.ffLoadingIv.visibility = View.VISIBLE
                }
            }
            setStartIconOnClickListener {
                //TODO как воспроизвести звук?
                Toast.makeText(context, "voice", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTranslateData(data: List<DomainModel>) {
        binding.ffLoadingIv.visibility = View.GONE

        when {
            data.isEmpty() -> {
                binding.ffTil.error = getString(R.string.ff_til_error_incorrect_input)
            }
            data[0].text != binding.ffTiet.text.toString() -> {
                //TODO придумать как лучше обработать response
                Toast.makeText(context, "Примерный перевод", Toast.LENGTH_LONG).show()
            }
        }
        adapter.submitList(data)
    }
}