package com.example.mygitapplication.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mygitapplication.R
import com.example.mygitapplication.databinding.UserItemBinding
import com.example.mygitapplication.model.User

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
) {
    private val binding = UserItemBinding.bind(itemView)

    fun bind(userEntity: User) {
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = userEntity.login
        binding.idTextView.text = userEntity.id.toString()
        binding.typeTextView.text = userEntity.type
    }

}