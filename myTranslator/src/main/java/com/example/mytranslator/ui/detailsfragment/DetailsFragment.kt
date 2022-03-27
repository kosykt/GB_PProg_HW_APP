package com.example.mytranslator.ui.detailsfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.gb_pprog.imageloader.ImageLoader
import com.example.mytranslator.databinding.FragmentDetailsBinding
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val args by navArgs<DetailsFragmentArgs>()

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding? = null")

    override fun onAttach(context: Context) {
        (requireActivity().application as TranslatorSubcomponentProvider).initTranslatorSubcomponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageLoader.loadInto(args.model.meanings[0].imageUrl, binding.detailsImageView)
        binding.detailsText.text = args.model.text
        binding.detailsTranscription.text = args.model.meanings[0].transcription
        binding.detailsTranslation.text = args.model.meanings[0].translation.text
        when(args.model.meanings[0].translation.note.isEmpty()){
            true -> binding.detailsNote.visibility = View.GONE
            false -> binding.detailsNote.text = args.model.meanings[0].translation.note
        }
    }
}