package com.example.gb_pprog.presentation.firstfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_pprog.databinding.FfItemBinding
import com.example.gb_pprog.domain.model.DomainModelItem

class FirstAdapter() :
    ListAdapter<DomainModelItem, FirstAdapter.FirstViewHolder>(FirstItemCallback) {

    inner class FirstViewHolder(private val vb: FfItemBinding) : RecyclerView.ViewHolder(vb.root) {

        fun showTranslate(dto: DomainModelItem){
            vb.ffItemTv.text = dto.meanings[0].translation.text
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


object FirstItemCallback : DiffUtil.ItemCallback<DomainModelItem>() {
    override fun areItemsTheSame(
        oldItem: DomainModelItem,
        newItem: DomainModelItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DomainModelItem,
        newItem: DomainModelItem
    ): Boolean {
        return oldItem == newItem
    }
}