package ru.kosykt.mylibrary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gb_pprog.domain.TestUseCase
import ru.kosykt.mylibrary.databinding.FragmentTestBinding
import javax.inject.Inject

class TestFragment : Fragment() {

    @Inject
    lateinit var testUseCase: TestUseCase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TestProvider).getTestComponent().inject(this)
    }

    private var _binding: FragmentTestBinding? = null
    private val binding: FragmentTestBinding
        get() = _binding ?: throw RuntimeException("FragmentTestBinding? = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.testTv.text = testUseCase.execute().toString()
    }
}