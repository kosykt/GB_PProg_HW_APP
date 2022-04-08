package ru.kosykt.githubusers.ui.usersfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kosykt.githubusers.databinding.UsersItemBinding
import ru.kosykt.githubusers.domain.models.DomainUserModel
import ru.kosykt.utils.imageloader.ImageLoader

class UsersAdapter(
    private val imageLoader: ImageLoader,
    private val navigate: (DomainUserModel) -> Unit,
) : ListAdapter<DomainUserModel, UsersAdapter.UsersViewHolder>(UsersItemCallback) {

    inner class UsersViewHolder(private val vb: UsersItemBinding) :
        RecyclerView.ViewHolder(vb.root) {
        fun showUser(model: DomainUserModel) {
            vb.root.setOnClickListener { navigate(model) }
            vb.usersItemTv.text = model.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            UsersItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.showUser(currentList[position])
    }

    companion object UsersItemCallback : DiffUtil.ItemCallback<DomainUserModel>() {
        override fun areItemsTheSame(
            oldItem: DomainUserModel,
            newItem: DomainUserModel,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DomainUserModel,
            newItem: DomainUserModel,
        ): Boolean {
            return oldItem == newItem
        }
    }
}