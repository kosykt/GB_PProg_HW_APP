package com.example.gb_pprog.presentation.firstfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_pprog.databinding.FfItemBinding
import com.example.gb_pprog.domain.model.DomainModel

class FirstAdapter() :
    ListAdapter<DomainModel, FirstAdapter.FirstViewHolder>(FirstItemCallback) {

    inner class FirstViewHolder(private val vb: FfItemBinding) : RecyclerView.ViewHolder(vb.root) {

        fun showTranslate(dto: DomainModel) {
            vb.ffItemTvTranslate.text = dto.meanings[0].translation.text
            if (dto.meanings[0].translation.note != ""){
                vb.ffItemTvNote.apply {
                    visibility = View.VISIBLE
                    text = dto.meanings[0].translation.note
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        return FirstViewHolder(
            FfItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        holder.showTranslate(currentList[position])
    }
}


object FirstItemCallback : DiffUtil.ItemCallback<DomainModel>() {
    override fun areItemsTheSame(
        oldItem: DomainModel,
        newItem: DomainModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DomainModel,
        newItem: DomainModel
    ): Boolean {
        return oldItem == newItem
    }
}