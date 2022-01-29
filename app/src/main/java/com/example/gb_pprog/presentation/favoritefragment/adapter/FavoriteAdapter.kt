package com.example.gb_pprog.presentation.favoritefragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_pprog.databinding.FavoriteItemBinding
import com.example.gb_pprog.domain.model.FavoriteModel

class FavoriteAdapter(
    private val onItemClickListener: (FavoriteModel) -> Unit,
) : ListAdapter<FavoriteModel, FavoriteAdapter.FavoriteViewHolder>(FavoriteItemCallback) {

    inner class FavoriteViewHolder(private val vb: FavoriteItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun showWords(model: FavoriteModel) {
            vb.favoriteItemDelete.setOnClickListener {
                onItemClickListener(model)
            }
            vb.favoriteItemWord.text = model.word
            vb.favoriteItemTranslation.text = model.translation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.showWords(currentList[position])
    }
}

object FavoriteItemCallback : DiffUtil.ItemCallback<FavoriteModel>() {
    override fun areItemsTheSame(
        oldItem: FavoriteModel,
        newItem: FavoriteModel,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: FavoriteModel,
        newItem: FavoriteModel,
    ): Boolean {
        return oldItem == newItem
    }
}