package com.example.myfirsttest.presenteration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirsttest.databinding.MyFirstTestItemBinding
import com.example.myfirsttest.domain.UseCaseModel

class MyFirstTestAdapter() :
    ListAdapter<UseCaseModel, MyFirstTestAdapter.MyItemViewHolder>(MyItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemViewHolder {
        return MyItemViewHolder(
            MyFirstTestItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MyItemViewHolder(
        private val vb: MyFirstTestItemBinding,
    ) : RecyclerView.ViewHolder(vb.root) {

        fun bind(model: UseCaseModel) {
            vb.repositoryName.text = model.text
        }
    }

    companion object MyItemCallback : DiffUtil.ItemCallback<UseCaseModel>() {
        override fun areItemsTheSame(
            oldItem: UseCaseModel,
            newItem: UseCaseModel,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: UseCaseModel,
            newItem: UseCaseModel,
        ): Boolean {
            return oldItem == newItem
        }
    }
}