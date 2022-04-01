package com.example.mytranslator.ui.detailsfragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mytranslator.databinding.FragmentDetailsBinding
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import ru.kosykt.utils.imageloader.ImageLoader
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

        imageLoader.loadInto(args.detailsModel.imageUrl, binding.detailsImageView)

        binding.detailsText.text = args.detailsModel.word
        binding.detailsTranscription.text = args.detailsModel.transcription
        binding.detailsTranslation.text = args.detailsModel.translation
        when (args.detailsModel.note.isEmpty()) {
            true -> binding.detailsNote.visibility = View.GONE
            false -> binding.detailsNote.text = args.detailsModel.note
        }

        binding.detailsSearchBtn.setOnClickListener {
            searchWeb(args.detailsModel.word)
        }
    }

    private fun searchWeb(word: String) {
        val webpage: Uri = Uri
            .parse("https://translate.google.com/?sl=en&tl=ru&text=${word}&op=translate")
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }
}