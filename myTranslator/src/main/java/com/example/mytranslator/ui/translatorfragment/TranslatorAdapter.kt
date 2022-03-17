package com.example.mytranslator.ui.translatorfragment

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_pprog.domain.model.DomainModel
import com.example.mytranslator.databinding.TranslatorItemBinding
import com.example.mytranslator.imageloader.ImageLoader

class TranslatorAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val onItemClickListener: (DomainModel) -> Boolean,
    private val checkIsFavorite: (DomainModel) -> Boolean,
    private val activeDrawable: Drawable?,
    private val diActiveDrawable: Drawable?,
    private val string: String,
) : ListAdapter<DomainModel, TranslatorAdapter.TranslatorViewHolder>(TranslatorItemCallback) {

    inner class TranslatorViewHolder(private val vb: TranslatorItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        private fun initClickListener(dto: DomainModel) {
            vb.translatorItemFavoriteBtn.setOnClickListener {
                if (onItemClickListener(dto)) {
                    vb.translatorItemFavoriteBtn.setImageDrawable(activeDrawable)
                } else {
                    vb.translatorItemFavoriteBtn.setImageDrawable(diActiveDrawable)
                }
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
                vb.translatorItemFavoriteBtn.setImageDrawable(activeDrawable)
            } else {
                vb.translatorItemFavoriteBtn.setImageDrawable(diActiveDrawable)
            }
        }

        private fun initNoteSetter(dto: DomainModel) {
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

        private fun initTextSetter(dto: DomainModel) {
            vb.translatorItemTvTranslate.text = String.format(
                string,
                dto.text,
                dto.meanings[0].translation.text
            )
        }

        fun showTranslate(dto: DomainModel) {
            initFavoriteChecker(dto)
            initImageLoader(dto)
            initClickListener(dto)
            initTextSetter(dto)
            initNoteSetter(dto)
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

    companion object TranslatorItemCallback : DiffUtil.ItemCallback<DomainModel>() {
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
}