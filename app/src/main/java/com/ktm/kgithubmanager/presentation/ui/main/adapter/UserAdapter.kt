package com.ktm.kgithubmanager.presentation.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ktm.kgithubmanager.databinding.ItemUserBinding
import com.ktm.kgithubmanager.domain.entity.User

class UserAdapter : PagingDataAdapter<User, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener: OnItemClickListener? = null

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.binding.apply {
                tvName.text = user.userName
                tvBlog.text = user.blogUrl
                Glide.with(ivAvatar.context)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(ivAvatar)
            }
            holder.binding.main.setOnClickListener {
                onItemClickListener?.onItemClicked(user)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.userName == newItem.userName
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(user: User)
    }
}