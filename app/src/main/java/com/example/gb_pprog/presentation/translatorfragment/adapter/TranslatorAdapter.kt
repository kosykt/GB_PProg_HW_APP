package com.example.gb_pprog.presentation.translatorfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_pprog.R
import com.example.gb_pprog.databinding.TranslatorItemBinding
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.presentation.imageloader.ImageLoader

class TranslatorAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val onItemClickListener: (DomainModel) -> Unit,
    private val checkIsFavorite: (DomainModel) -> Boolean,
) : ListAdapter<DomainModel, TranslatorAdapter.TranslatorViewHolder>(TranslatorItemCallback) {

    inner class TranslatorViewHolder(private val vb: TranslatorItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        private fun initClickListener(dto: DomainModel) {
            vb.translatorItemFavoriteBtn.setOnClickListener {
                onItemClickListener(dto)
            }
        }

        private fun initImageLoader(dto: DomainModel) {
            imageLoader.loadInto(
                url = dto.meanings[0].imageUrl,
                vb.translatorItemIv
            )
        }

        private fun initFavoriteChecker(dto: DomainModel) {
            if (checkIsFavorite(dto)) {
                vb.translatorItemFavoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_active)
            }else {
                vb.translatorItemFavoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_diactive)
            }
        }

        fun showTranslate(dto: DomainModel) {
            initImageLoader(dto)
            initClickListener(dto)
            initFavoriteChecker(dto)
            vb.translatorItemTvTranslate.text = String.format(
                itemView.context.getString(R.string.translator_item_tv_translate_text),
                dto.text,
                dto.meanings[0].translation.text
            )
            dto.meanings[0]
            if (dto.meanings[0].translation.note == "") {
                vb.translatorItemTvNote.apply {
                    visibility = View.GONE
                    text = ""
                }
            } else {
                vb.translatorItemTvNote.apply {
                    visibility = View.VISIBLE
                    text = dto.meanings[0].translation.note
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslatorViewHolder {
        return TranslatorViewHolder(
            TranslatorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TranslatorViewHolder, position: Int) {
        holder.showTranslate(currentList[position])
    }
}


object TranslatorItemCallback : DiffUtil.ItemCallback<DomainModel>() {
    override fun areItemsTheSame(
        oldItem: DomainModel,
        newItem: DomainModel,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DomainModel,
        newItem: DomainModel,
    ): Boolean {
        return oldItem == newItem
    }
}