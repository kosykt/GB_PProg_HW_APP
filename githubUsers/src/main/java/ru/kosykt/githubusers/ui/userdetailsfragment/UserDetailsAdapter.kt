package ru.kosykt.githubusers.ui.userdetailsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kosykt.githubusers.databinding.DetailsItemBinding
import ru.kosykt.githubusers.domain.models.DomainUserDetailsModel

class UserDetailsAdapter :
    ListAdapter<DomainUserDetailsModel, UserDetailsAdapter.DetailsViewHolder>(DetailsItemCallback) {

    inner class DetailsViewHolder(private val vb: DetailsItemBinding) :
        RecyclerView.ViewHolder(vb.root) {
        fun show(model: DomainUserDetailsModel) {
            vb.detailsItemTv.text = model.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(
            DetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.show(currentList[position])
    }

    companion object DetailsItemCallback : DiffUtil.ItemCallback<DomainUserDetailsModel>() {
        override fun areItemsTheSame(
            oldItem: DomainUserDetailsModel,
            newItem: DomainUserDetailsModel,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DomainUserDetailsModel,
            newItem: DomainUserDetailsModel,
        ): Boolean {
            return oldItem == newItem
        }
    }
}