package com.example.gb_pprog.presentation.firstfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_pprog.data.network.model.RetrofitTranslateDtoItem
import com.example.gb_pprog.databinding.FfItemBinding

class FirstAdapter() :
    ListAdapter<RetrofitTranslateDtoItem, FirstAdapter.FirstViewHolder>(FirstItemCallback) {

    inner class FirstViewHolder(private val vb: FfItemBinding) : RecyclerView.ViewHolder(vb.root) {

        fun showTranslate(dto: RetrofitTranslateDtoItem){
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


object FirstItemCallback : DiffUtil.ItemCallback<RetrofitTranslateDtoItem>() {
    override fun areItemsTheSame(
        oldItem: RetrofitTranslateDtoItem,
        newItem: RetrofitTranslateDtoItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RetrofitTranslateDtoItem,
        newItem: RetrofitTranslateDtoItem
    ): Boolean {
        return oldItem == newItem
    }
}