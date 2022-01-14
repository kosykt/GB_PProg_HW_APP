package com.example.gb_pprog.presentation.firstfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.databinding.FragmentFirstBinding
import com.example.gb_pprog.domain.SearchWordUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding? = null")

    private val domainRepository = DomainRepositoryImpl()
    private val searchWordUseCase = SearchWordUseCase(domainRepository)

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
            Toast.makeText(context, "asd", Toast.LENGTH_LONG).show()
        }
        searchWordUseCase.execute("word")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dto ->
                test(dto)
            }
    }

    private fun test(dto: RetrofitTranslateDto) {
        val asd = dto[0].text
        Log.d("testRetrofit", asd)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}